package com.dh.daddy.scoffee.Controllers;

import com.dh.daddy.scoffee.Dto.JWT.JwtRequest;
import com.dh.daddy.scoffee.Dto.JWT.JwtResponse;
import com.dh.daddy.scoffee.Dto.User.UserCreateDto;
import com.dh.daddy.scoffee.Dto.User.UserDto;
import com.dh.daddy.scoffee.Models.User;
import com.dh.daddy.scoffee.Security.CustomUserDetailsService;
import com.dh.daddy.scoffee.Services.UserService;
import com.dh.daddy.scoffee.Utility.JWTUtility;
import com.dh.daddy.scoffee.Utility.JwtDecodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private JwtDecodeService jwtDecodeService;
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTUtility jwtUtility;


    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody UserCreateDto userCreateDto ){

        boolean res = service.isUsernameAlreadyTaken(userCreateDto.getUsername());
        if(res == !true){
            ModelMapper modelMapper = new ModelMapper();

            User u = modelMapper.map(userCreateDto , User.class);

            u.setPermissionLevel("user");
            u.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));

            User createdUser = service.saveUser(u);

            return new ResponseEntity<>(modelMapper.map(createdUser , UserDto.class) , HttpStatus.OK ) ;
        }
        return new ResponseEntity<>( "username Or email Already Exist" , HttpStatus.CONFLICT );
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try{
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            ));
        }catch (BadCredentialsException e){
            throw  new Exception("username password error" , e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        // add custom user Authentications
        Map<String, Object> claims = new HashMap<>();
        User u = service.getUser(jwtRequest.getUsername());
        claims.put( "permissionLevel" , u.getPermissionLevel() );

        final String token = jwtUtility.generateToken( claims , userDetails);



        return  new JwtResponse(token);
    }
}
