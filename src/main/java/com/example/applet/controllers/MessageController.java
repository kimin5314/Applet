package com.example.applet.controllers;

import com.example.applet.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/dialogue/{receiverId}/{senderId}")
    public ResponseEntity<?> getDialogue(@PathVariable Long receiverId, @PathVariable Long senderId) {
        try {
            return ResponseEntity.ok(messageService.getDialogue(receiverId, senderId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
