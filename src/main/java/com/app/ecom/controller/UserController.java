package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
import com.app.ecom.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return new ResponseEntity<>(userService.fetchAllUsers(),
                HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity <UserResponse> getUsers(@PathVariable Long id){
       return userService.fetchUsers(id)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping ()
    public ResponseEntity<String> createUsers(@RequestBody UserRequest userRequest){
        userService.addUsers(userRequest);
        return ResponseEntity.ok("User added successfully ");
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserRequest updatedUserRequest){
        boolean upadate = userService.updateUser(id,updatedUserRequest);
        if(upadate)
            return ResponseEntity.ok("User updated successfully ");
        return ResponseEntity.notFound().build();

    }

}
