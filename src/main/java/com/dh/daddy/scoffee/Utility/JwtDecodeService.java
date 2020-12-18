package com.dh.daddy.scoffee.Utility;

import com.dh.daddy.scoffee.Dto.JWT.TokenPayloadDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class JwtDecodeService {

    public TokenPayloadDto decode() {


        try{

            String token = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest()
                .getHeader("Authorization");

            String payload = token.split("\\.")[1];

            String decodePayload = new String(Base64.decodeBase64(payload) , "UTF-8" ) ;

            JSONObject jOb = new JSONObject(decodePayload);

            String permissionLevel = jOb.getString("permissionLevel");
            String username = jOb.getString("sub");

            TokenPayloadDto tokenPayloadDto = new TokenPayloadDto();
            tokenPayloadDto.setUsername(username);
            tokenPayloadDto.setPermissionLevel(permissionLevel);

            return tokenPayloadDto;

        }catch (Exception e){
            return null;
        }
    }
}
