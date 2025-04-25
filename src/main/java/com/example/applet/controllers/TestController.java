package com.example.applet.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    public TestController() {
    }

    @GetMapping("/1")
    public ResponseEntity<String> test() {
        try {
                return ResponseEntity.ok().body("111");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
