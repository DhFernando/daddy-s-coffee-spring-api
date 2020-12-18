package com.dh.daddy.scoffee.Services;


import com.dh.daddy.scoffee.Interfaces.IUserRepository;
import com.dh.daddy.scoffee.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepository repo;

    public List<User> getAll(){ return repo.findAll(); }


    public User saveUser(User person){ return repo.save(person); }

    public User getUser(String username){ return repo.findUserByUsername(username); }

    public boolean isUsernameAlreadyTaken( String username ) {
        if( repo.findUsernameAlreadyTaken(username ) == 1 ){
            return true;
        }else{
            return false;
        }

    }



    public void setPermission(String username, String setPermissionAs) {
        repo.updatePermission(username , setPermissionAs);
    }

    public void deleteUser(String username) { repo.removeUser(username); }
}
