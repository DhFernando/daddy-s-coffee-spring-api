package com.dh.daddy.scoffee.Interfaces;

import com.dh.daddy.scoffee.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IItemRepository extends JpaRepository<Item , Integer> {
    @Query(value = "SELECT * FROM items WHERE Id = :id" , nativeQuery = true)
    Item fetchItemById( @Param("id") Integer id);
}
