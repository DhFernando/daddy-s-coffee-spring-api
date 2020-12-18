package com.dh.daddy.scoffee.Controllers;

import com.dh.daddy.scoffee.Dto.Item.ItemCreateDto;
import com.dh.daddy.scoffee.Dto.Item.ItemDto;
import com.dh.daddy.scoffee.Models.Item;
import com.dh.daddy.scoffee.Services.ItemService;
import com.dh.daddy.scoffee.Utility.CustomJwtTokenTokenDetails;
import com.dh.daddy.scoffee.Utility.JwtDecodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private JwtDecodeService jwtDecodeService;

    @PostMapping(value = "/item")
    public ResponseEntity<?> addItem(@RequestBody ItemCreateDto itemCreateDto){
        try{
            if(Objects.nonNull(itemCreateDto)){
                ModelMapper modelMapper = new ModelMapper();
                Item i  = modelMapper.map( itemCreateDto , Item.class);
                i.setItemCreator( jwtDecodeService.decode().getUsername() );

               try{
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

    @DeleteMapping(value = "/item/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        try {
            ModelMapper modelMapper = new ModelMapper();
            Item fetchedItem = itemService.findItem( id );
            if(Objects.nonNull(fetchedItem)){
                itemService.remove(fetchedItem);
                return new ResponseEntity<>("Item Removed" , HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Item not Found" , HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
