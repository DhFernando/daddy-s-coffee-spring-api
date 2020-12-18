package com.dh.daddy.scoffee.Dto.Item;

import lombok.Data;

@Data
public class ItemDto {
    private Integer Id;
    private String itemName;
    private Integer price;
    private String description;
    private String itemCreator;
}
