package com.drew.Reddit.api;

import com.drew.Reddit.models.User;
import com.drew.Reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.listAllUser();
    }

    @GetMapping("/status/{id}")
    public String checkStatus(@PathVariable Integer id){
        return userService.checkAuthStatus(id).toString();
    }

    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.saveUser(user);
    }

    @PostMapping("/auth/{id}")
    public void authorize(@PathVariable Integer id){
        userService.userAuthenticate(id);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteAcc(@PathVariable Integer id){
        userService.deleteUser(id);

        return "DELETE SUCCESSFUL";
    }

}
