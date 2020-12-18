package com.dh.daddy.scoffee.Dto.User;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
    private String imageUrl;
}

