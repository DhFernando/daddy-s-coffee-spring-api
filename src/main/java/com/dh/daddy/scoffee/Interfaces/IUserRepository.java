package com.dh.daddy.scoffee.Interfaces;

import com.dh.daddy.scoffee.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query( value = "SELECT * FROM users WHERE username = :username" , nativeQuery = true )
    User findUserByUsername( @Param("username") String username );

    @Query(value = "SELECT COUNT(1) FROM users WHERE username = :username " , nativeQuery = true)
    Integer findUsernameAlreadyTaken(@Param("username") String username  );

    @Query(value = "SELECT users SET permissionLevel = :setPermissionAs WHERE username = :username" , nativeQuery = true)
    void updatePermission( @Param("username") String username,@Param("setPermissionAs") String setPermissionAs);

    @Query(value = "DELETE FROM users WHERE username = :username" , nativeQuery = true)
    void removeUser(@Param("username") String username);
}
