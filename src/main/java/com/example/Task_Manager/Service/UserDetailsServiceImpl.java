package com.example.Task_Manager.Service;

import com.example.Task_Manager.Entity.User;
import com.example.Task_Manager.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private User_Repository user_repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = user_repository.findByusername(username);
        if(user!= null){
           UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                   .username(user.getUsername())
                   .password(user.getPassword())
                   .roles(user.getRoles().toArray(new String[0]))
                   .build();
           return userDetails;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
