package com.gg.sms.controllers;

import com.gg.sms.dto.UserDto;
import com.gg.sms.entity.User;
import com.gg.sms.response.JsonResponse;
import com.gg.sms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<JsonResponse> createUser(@RequestBody UserDto userDto){
        JsonResponse userResponse = userService.createUser(userDto);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JsonResponse> findUser(@PathVariable Long id){
        JsonResponse user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JsonResponse> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        JsonResponse userResponse = userService.updateUser(id, userDto);
        return ResponseEntity.ok(userResponse);
    }
@DeleteMapping("/delete/{id}")
    public ResponseEntity<JsonResponse> deleteUser(@PathVariable Long id){
        JsonResponse user = userService.deleteUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/findAll")
    public ResponseEntity<JsonResponse> FindAllUsers(){
        JsonResponse users = userService.findAllUser();
        return ResponseEntity.ok(users);
    }
}

