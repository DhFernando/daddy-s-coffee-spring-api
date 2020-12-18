package com.dh.daddy.scoffee.Dto.Order;

import lombok.Data;

@Data
public class OrderedItemDto {
    private Integer orderId;
    private Integer itemId;
    private Integer quantity;
}
