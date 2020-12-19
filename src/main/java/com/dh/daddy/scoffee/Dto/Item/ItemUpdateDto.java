package com.dh.daddy.scoffee.Dto.Item;

import lombok.Data;

@Data
public class ItemUpdateDto {
    private String itemName;
    private Integer price;
    private String description;
}
