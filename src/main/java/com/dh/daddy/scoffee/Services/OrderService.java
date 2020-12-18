package com.dh.daddy.scoffee.Services;

import com.dh.daddy.scoffee.Dto.Order.OrderedItemCreateDto;
import com.dh.daddy.scoffee.Interfaces.IOrderRepository;
import com.dh.daddy.scoffee.Models.Order;
import com.dh.daddy.scoffee.Models.OrderedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    public Order saveOrder( Order order ) { return iOrderRepository.save(order); }
    public void insertOrderedItems( Integer orderId ,List<OrderedItemCreateDto> orderedItemCreateDtos) {

        for ( OrderedItemCreateDto orderedItemCreateDto : orderedItemCreateDtos) {
            iOrderRepository.insertOrderedItems(
                    orderId,
                    orderedItemCreateDto.getItemId(),
                    orderedItemCreateDto.getQuantity()
            );
        }
    }

    public Order getOrderByOrderId(Integer orderId){
        return iOrderRepository.fetchOrder(orderId);
    }

    public void completeOrderProcess(Integer orderId) {
        iOrderRepository.completeOrder(orderId , new Date());
    }

    public List<Order> getAllOrders(){ return iOrderRepository.findAll(); }
}
