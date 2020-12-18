package com.dh.daddy.scoffee.Services;

import com.dh.daddy.scoffee.Interfaces.IOrderedItemRepository;
import com.dh.daddy.scoffee.Models.OrderedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedItemService {
    @Autowired
    IOrderedItemRepository iOrderedItemRepository;

    public OrderedItem insertOrderedItems(OrderedItem orderedItem) {
            return iOrderedItemRepository.save(orderedItem);
    }

    public List<OrderedItem> getOrderedItemsByOderId(Integer orderId) {
        return iOrderedItemRepository.fetchOrderedItemsByOderId(orderId);
    }
}
