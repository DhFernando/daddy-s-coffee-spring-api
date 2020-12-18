package com.dh.daddy.scoffee.Dto.JWT;

import lombok.Data;

@Data
public class TokenPayloadDto {
    private String username;
    private String permissionLevel;
}
