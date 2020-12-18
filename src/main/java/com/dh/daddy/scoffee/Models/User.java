package com.dh.daddy.scoffee.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "permissionLevel")
    private String permissionLevel;

    @Column(name = "imageUrl")
    private String imageUrl;
}
