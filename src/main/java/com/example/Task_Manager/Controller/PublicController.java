package com.example.Task_Manager.Controller;

import com.example.Task_Manager.Entity.User;
import com.example.Task_Manager.Repository.Task_Repository;
import com.example.Task_Manager.Service.TaskService;
import com.example.Task_Manager.Service.UserService;
import com.example.Task_Manager.Utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@RequestBody User user){
        if(user !=null){
            userService.CreateUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Login")
    public ResponseEntity<?> Login(@RequestBody User user){
try {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
    String jwt = jwtUtil.generateToken(userDetails.getUsername());
    return new ResponseEntity<>(jwt , HttpStatus.OK);
}catch(Exception e) {
    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
}
}
}
