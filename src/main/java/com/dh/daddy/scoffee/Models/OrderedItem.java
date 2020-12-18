package com.dh.daddy.scoffee.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orderedItems")
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "orderId")
    private Integer orderId;
    @Column(name = "itemId")
    private Integer itemId;
    @Column(name = "quantity")
    private Integer quantity;
}
