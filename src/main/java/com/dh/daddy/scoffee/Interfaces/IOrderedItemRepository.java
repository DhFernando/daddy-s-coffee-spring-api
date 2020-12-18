package com.dh.daddy.scoffee.Interfaces;

import com.dh.daddy.scoffee.Models.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderedItemRepository extends JpaRepository<OrderedItem , Integer> {

    @Query(value = "SELECT * FROM ordereditems WHERE orderId = :orderId" , nativeQuery = true)
    List<OrderedItem> fetchOrderedItemsByOderId( @Param("orderId") Integer orderId);
}
