package com.dh.daddy.scoffee.Controllers;

import com.dh.daddy.scoffee.Dto.Item.ItemCreateDto;
import com.dh.daddy.scoffee.Dto.Item.ItemDto;
import com.dh.daddy.scoffee.Dto.Item.ItemUpdateDto;
import com.dh.daddy.scoffee.Models.Item;
import com.dh.daddy.scoffee.Services.ItemService;
import com.dh.daddy.scoffee.Utility.JwtDecodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private JwtDecodeService jwtDecodeService;

    @PostMapping(value = "/item")
    public ResponseEntity<?> addItem(@RequestBody ItemCreateDto itemCreateDto){
        try{
            // check dto is null or not
            if(Objects.nonNull(itemCreateDto)){

                // map dto to item model
                ModelMapper modelMapper = new ModelMapper();
                Item i  = modelMapper.map( itemCreateDto , Item.class);
                i.setItemCreator( jwtDecodeService.decode().getUsername() );
                
               try{

                   // try to save the item to database
                   Item createdItem = itemService.saveItem(i);

                   return new ResponseEntity<>(modelMapper.map(createdItem , ItemDto.class) , HttpStatus.OK);
               }catch (Exception e){
                   return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
               }
            }else{
                return new ResponseEntity<>("Bad Request" , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items")
    public ResponseEntity<?> fetchAllItems(){
        try{

            return new ResponseEntity<>( itemService.getAllItems().stream().map(item -> {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map( item , ItemDto.class );
            }).collect(Collectors.toList()) , HttpStatus.OK ) ;

        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong" , HttpStatus.CONFLICT);
        }
    }

    // fetch item by item id
    @GetMapping(value = "/item/{id}")
    public ResponseEntity<?> getItem(@PathVariable Integer id){
        try {
            ModelMapper modelMapper = new ModelMapper();
            Item fetchedItem = itemService.findItem( id );
            ItemDto item = modelMapper.map(fetchedItem , ItemDto.class);
            return new ResponseEntity<>(item , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Item Not Found" , HttpStatus.NOT_FOUND);
        }
    }

    // delete items
    @DeleteMapping(value = "/item/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        try {
            // check item is on BD or not
            Item fetchedItem = itemService.findItem( id );
            if(Objects.nonNull(fetchedItem)){

                //call to service to delete item
                itemService.remove(fetchedItem);
                return new ResponseEntity<>("Item Removed" , HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Item not Found" , HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Item update
    @PutMapping("item/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Integer id , @RequestBody ItemUpdateDto updatedItem){
        try {
            Item i = itemService.findItem(id);
            if(Objects.nonNull(i)){
                if(Objects.nonNull(updatedItem)){
                    ModelMapper modelMapper = new ModelMapper();

                    i.setItemCreator(jwtDecodeService.decode().getUsername());
                    i.setDescription(updatedItem.getDescription());
                    i.setItemName(updatedItem.getItemName());
                    i.setPrice( updatedItem.getPrice() );

                    itemService.saveItem( i ) ;

                    return new ResponseEntity<>(i , HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("Invalid updated item" , HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>( "Item not found" , HttpStatus.NOT_FOUND );
            }
        }catch (Exception e){
            return new ResponseEntity<>("Internal server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
