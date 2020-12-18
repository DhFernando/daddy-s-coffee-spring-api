package com.dh.daddy.scoffee.Dto.Item;

import lombok.Data;

@Data
public class ItemCreateDto {
    private String itemName;
    private Integer price;
    private String description;
}
