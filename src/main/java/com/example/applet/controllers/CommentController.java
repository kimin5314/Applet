package com.example.applet.controllers;

import com.example.applet.entities.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.applet.services.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Comment comment) {
        try {
            commentService.create(comment);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{resourceId}")
    public ResponseEntity<?> getCommentList(@PathVariable Long resourceId) {
        try {
            return ResponseEntity.ok(commentService.getCommentList(resourceId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            commentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
