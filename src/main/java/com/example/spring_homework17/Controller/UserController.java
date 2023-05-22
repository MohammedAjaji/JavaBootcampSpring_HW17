package com.example.spring_homework17.Controller;

import com.example.spring_homework17.Model.User;
import com.example.spring_homework17.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllCoffee(){
        List<User> userList = userService.getAllUser();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, @PathVariable Integer id, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = userService.updateUser(id,user);
        if (isUpdated){
            return ResponseEntity.status(200).body("User Updated");
        }
        return ResponseEntity.status(400).body("ID cannot be found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser( @PathVariable Integer id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity.status(200).body("User Deleted");
        }
        return ResponseEntity.status(400).body("ID cannot be found");
    }
}
