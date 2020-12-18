package com.dh.daddy.scoffee.Dto.Order;

import com.sun.istack.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Integer orderId;
    private Integer contactNumber;
    private String ownerNIC;
    private String deliveryAddress;
    private String orderOwner;

    @Nullable
    private List<OrderedItemDto> listOfOrders;
}
