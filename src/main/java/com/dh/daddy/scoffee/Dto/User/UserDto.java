package com.dh.daddy.scoffee.Dto.User;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String username;
    private String permissionLevel;
    private String imageUrl;
}
