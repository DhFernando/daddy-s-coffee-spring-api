package com.dh.daddy.scoffee.Dto.Order;

import com.sun.istack.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {
    private Integer contactNumber;
    private String deliveryAddress;
    private String orderOwner;
    private String ownerNIC;

    @Nullable
    private List<OrderedItemCreateDto> listOfOrders;
}
