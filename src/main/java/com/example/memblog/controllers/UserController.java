package com.example.memblog.controllers;

import com.example.memblog.entities.UserEntity;
import com.example.memblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.Boolean.TRUE;

//todo определиться маппингами (ссылками)
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setMemeService(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/newuser")
    public ResponseEntity saveUser(@RequestBody UserEntity user){
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("done");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    }

    @PostMapping("/enter")
    public ResponseEntity Entrance(@RequestBody UserEntity user){

        try {
            return ResponseEntity.ok(userService.checkUser(user));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("wrong password or username");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("done");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("wrong password or username");
        }
    }

}
