package com.dh.daddy.scoffee.Interfaces;

import com.dh.daddy.scoffee.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;

public interface IOrderRepository extends JpaRepository<Order , Integer> {
    @Modifying
    @Query( value =
            "INSERT INTO ordereditems (orderId, itemId, quantity) VALUES (:orderId, :itemId, :quantity )"
            , nativeQuery = true )
    @Transactional
    void insertOrderedItems(@Param("orderId") Integer orderId ,
                                      @Param("itemId") Integer itemId , @Param("quantity") Integer quantity );

    @Query(value = "SELECT * FROM orders WHERE orderId = :orderId" , nativeQuery = true)
    Order fetchOrder( @Param("orderId") Integer orderId );

    @Modifying
    @Query(value = "UPDATE orders SET orderCompletedDate = :date WHERE orderId = :orderId " , nativeQuery = true)
    @Transactional
    void completeOrder( @Param("orderId") Integer orderId, @Param("date") Date date);
}
