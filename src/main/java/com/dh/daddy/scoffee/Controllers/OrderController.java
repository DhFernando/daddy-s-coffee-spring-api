package com.dh.daddy.scoffee.Controllers;

import com.dh.daddy.scoffee.Dto.Item.ItemDto;
import com.dh.daddy.scoffee.Dto.Order.OrderCreateDto;
import com.dh.daddy.scoffee.Dto.Order.OrderDto;
import com.dh.daddy.scoffee.Dto.Order.OrderedItemCreateDto;
import com.dh.daddy.scoffee.Dto.Order.OrderedItemDto;
import com.dh.daddy.scoffee.Models.Item;
import com.dh.daddy.scoffee.Models.Order;
import com.dh.daddy.scoffee.Models.OrderedItem;
import com.dh.daddy.scoffee.Services.ItemService;
import com.dh.daddy.scoffee.Services.OrderService;
import com.dh.daddy.scoffee.Services.OrderedItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderedItemService orderedItemService;
    @Autowired
    private ItemService itemService;

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDto orderCreateDto){
        try{
            if(Objects.nonNull(orderCreateDto)){
                ModelMapper modelMapper = new ModelMapper();
                Order o = modelMapper.map(orderCreateDto , Order.class);
                o.setOrderedDate(new Date());

               try {

                   if(Objects.nonNull(orderCreateDto.getListOfOrders())){
                           Order savedOrdered = orderService.saveOrder(o);
                      try{
                          if( Objects.nonNull(savedOrdered.getOrderId()) ){
                              List<OrderedItemDto> savedOrders = new ArrayList<>();

                              for ( OrderedItemCreateDto orderedItemCreateDto : orderCreateDto.getListOfOrders() ) {
                                  OrderedItem orderedItem = modelMapper.map(orderedItemCreateDto, OrderedItem.class );

                                  orderedItem.setOrderId(savedOrdered.getOrderId());

                                  OrderedItem savedItem = orderedItemService.insertOrderedItems(orderedItem);

                                  Item  item  = itemService.findItem(savedItem.getItemId());
                                  OrderedItemDto finalOrderedItemDto =  modelMapper.map( savedItem , OrderedItemDto.class );
                                  if(Objects.nonNull(item)){
                                      finalOrderedItemDto.setItem(modelMapper.map(item , ItemDto.class));
                                  }
                                  savedOrders.add( finalOrderedItemDto );
                              }

                              OrderDto completedOrder = modelMapper.map( savedOrdered , OrderDto.class );
                              completedOrder.setListOfOrders(savedOrders);

                              return new ResponseEntity<>( completedOrder , HttpStatus.OK );
                          }else{
                              return new ResponseEntity<>( "Null of Order" , HttpStatus.CONFLICT);
                          }

                      }catch (Exception e){
                          return new ResponseEntity<>( "Cannot Save orderedItems" , HttpStatus.CONFLICT);
                      }
                   }
                   return new ResponseEntity<>( "Request with no items " , HttpStatus.NOT_FOUND);
               }catch (Exception e){
                   return new ResponseEntity<>( "Bad request" , HttpStatus.BAD_REQUEST);
               }



            }else {
                return new ResponseEntity<>( "Bad request" , HttpStatus.BAD_REQUEST );
            }
        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer orderId){

        Order oderRecode = orderService.getOrderByOrderId(orderId);
        List<OrderedItemDto> items = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for ( OrderedItem orderedItem : orderedItemService.getOrderedItemsByOderId(orderId) ) {

            OrderedItemDto orderedItemToDto = modelMapper.map( orderedItem , OrderedItemDto.class );

            OrderedItemDto orderItemToOrderItemDto = modelMapper.map( orderedItem , OrderedItemDto.class );

            // get item by id
            Item i = itemService.findItem(orderedItem.getItemId());

            // check item is available or not
            if(Objects.nonNull(i)){
                // 1 -> convert item to Dto and set dto item in orderedItem
                orderItemToOrderItemDto.setItem(modelMapper.map( i , ItemDto.class ));
            }
            items.add(orderItemToOrderItemDto);
        }

        // convert oder to oderDto
        OrderDto completeOrder = modelMapper.map( oderRecode , OrderDto.class );

        // set item list to this
        completeOrder.setListOfOrders(items);

        return new ResponseEntity<>(completeOrder, HttpStatus.OK);

    }

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(){

        List<OrderDto> fullOrders = new ArrayList<>();

        try{
            ModelMapper modelMapper = new ModelMapper();
            // get all orders and fetch
            for ( Order order : orderService.getAllOrders() ) {
                OrderDto fetchedOrder = modelMapper.map(order , OrderDto.class);

                if(Objects.nonNull(order.getOrderId())){
                    List<OrderedItem> fetchedOrderedItems = orderedItemService.getOrderedItemsByOderId(order.getOrderId());

                    List<OrderedItemDto> orderedItemDtos = new ArrayList<>();

                    for ( OrderedItem fetchedOrderedItem : fetchedOrderedItems ) {

                        OrderedItemDto fetchOrderedItemToOrderedItemToDto =
                                modelMapper.map(fetchedOrderedItem , OrderedItemDto.class);

                        // fetch item by fetched oder's item id
                        Item i = itemService.findItem(fetchedOrderedItem.getItemId());

                        // check item is available or not
                        if(Objects.nonNull(i)){

                            // convert available item to dto and add to oder item list
                            fetchOrderedItemToOrderedItemToDto.setItem( modelMapper.map(i , ItemDto.class) );
                        }

                        orderedItemDtos.add(fetchOrderedItemToOrderedItemToDto);
                    }
                    fetchedOrder.setListOfOrders( orderedItemDtos );

                    fullOrders.add(fetchedOrder);
                }else {
                    return new ResponseEntity<>( "Conflict" , HttpStatus.CONFLICT );
                }
            }

            return new ResponseEntity<>( fullOrders , HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( "Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

    @PutMapping("/order/{orderId}/complete")
    public ResponseEntity<?> oderMakeComplete(@PathVariable Integer orderId){
        try{
            Order order = orderService.getOrderByOrderId(orderId);
            if(Objects.isNull( order.getOrderCompletedDate() )){
                try{
                    orderService.completeOrderProcess( order.getOrderId() );
                    return new ResponseEntity<>("Process Completed" , HttpStatus.OK);
                }catch (Exception e){
                    return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else{
                return new ResponseEntity<>("Order already completed" , HttpStatus.ALREADY_REPORTED);
            }
        }catch (Exception e){
            return new ResponseEntity<>( "Order Not Found" , HttpStatus.NOT_FOUND );
        }
    }

}
