package com.example.applet.controllers;

import com.example.applet.entities.User;
import com.example.applet.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            if (user.getPhone().length() != 11) {
                return ResponseEntity.badRequest().build();
            }
            User loginUser = userService.login(user.getPhone(), user.getRole());
            return ResponseEntity.ok().body(loginUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("Please login first");
            }
            return ResponseEntity.ok(userService.update(id, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
