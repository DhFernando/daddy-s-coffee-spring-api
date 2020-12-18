package com.dh.daddy.scoffee.Dto.Order;

import com.dh.daddy.scoffee.Dto.Item.ItemDto;
import lombok.Data;

@Data
public class OrderedItemDto {
    private Integer orderId;
    private ItemDto item;
    private Integer quantity;
}
