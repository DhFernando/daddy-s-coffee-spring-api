package com.dh.daddy.scoffee.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "contactNumber")
    private Integer contactNumber;
    @Column(name = "deliveryAddress")
    private String deliveryAddress;
    @Column(name = "orderOwner")
    private String orderOwner;
    @Column(name = "orderedDate")
    private Date orderedDate;
    @Column(name = "orderCompletedDate")
    private Date orderCompletedDate;
    @Column(name = "ownerNIC")
    private String ownerNIC;
}
