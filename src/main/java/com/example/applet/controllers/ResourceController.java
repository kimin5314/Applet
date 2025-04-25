package com.example.applet.controllers;

import com.example.applet.entities.*;
import com.example.applet.services.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/resource")
public class ResourceController {
    private final ResourceService resourceService;
    private final MediaService mediaService;
    private final CommentService commentService;

    public ResourceController(ResourceService resourceService, MediaService mediaService, CommentService commentService) {
        this.resourceService = resourceService;
        this.mediaService = mediaService;
        this.commentService = commentService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(resourceService.resourceBrief(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail/{userId}/{resourceId}")
    public ResponseEntity<?> detail(@PathVariable Long resourceId, @PathVariable Long userId) {
        try {
            return ResponseEntity.ok(resourceService.getDetail(resourceId, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> list(@PathVariable Long userId) {
        try {
            Iterable<Resource> resources = resourceService.findMyResource(userId);
            return ResponseEntity.ok(List.of(resources));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/recommend")
    public ResponseEntity<?> recommend() {
        try {
            return ResponseEntity.ok(resourceService.recommendResource());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<?> save(@RequestBody Resource resource) {
        try {
            return ResponseEntity.ok().body(resourceService.publish(resource));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Resource resource) {
        try {
            return ResponseEntity.ok().body(resourceService.update(id, resource));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            mediaService.deleteAllByResourceId(id);
            commentService.deleteAllByResourceId(id);
            resourceService.deleteResourceById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/favorite/{userId}/{resourceId}")
    public ResponseEntity<?> favorite(@PathVariable Long resourceId, @PathVariable Long userId) {
        try {
            resourceService.favorite(new Favorite(userId, resourceId));
            return ResponseEntity.ok().build();
        } catch (
                Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/unfavorite/{userId}/{resourceId}")
    public ResponseEntity<?> unfavorite(@PathVariable Long resourceId, @PathVariable Long userId) {
        try {
            resourceService.deleteFavorite(new Favorite(userId, resourceId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/favoriteList/{userId}")
    public ResponseEntity<?> favoriteList(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(resourceService.getFavoriteList(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> history(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(resourceService.getHistoryList(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String keyword) {
        try {
            return ResponseEntity.ok(resourceService.search(keyword));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search/autoCompleteList")
    public ResponseEntity<?> searchAutocompleteList(@RequestParam String keyword) {
        try {
            return ResponseEntity.ok(resourceService.searchAutocompleteList(keyword));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/toCheck")
    public ResponseEntity<?> toCheck() {
        try {
            return ResponseEntity.ok(resourceService.toCheck());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/check/{id}/{status}")
    public ResponseEntity<?> check(@PathVariable Long id, @PathVariable String status) {
        try {
            return ResponseEntity.ok(resourceService.check(id, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
