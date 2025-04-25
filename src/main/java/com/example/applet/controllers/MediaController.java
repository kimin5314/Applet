package com.example.applet.controllers;

import com.example.applet.entities.Media;
import com.example.applet.services.MediaService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/media")
public class MediaController {
    @Value("${media.path}")
    private String MEDIA_PATH;
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    private String storeFile(MultipartFile file, String resourceId) throws Exception {
        String fileName = file.getOriginalFilename();
        File dest = new File(MEDIA_PATH + resourceId + "/" + fileName);
        try {
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdirs()) {
                    throw new Exception("unable to create directory");
                }
            }
            file.transferTo(dest);
            return resourceId + "/" + fileName;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/upload/{resourceId}")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @PathVariable String resourceId) {
        try {
            String url = storeFile(file, String.valueOf(resourceId));
            Long id = Long.parseLong(resourceId);
            mediaService.saveMedia(new Media(url, id));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{prefixPath:.+}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String prefixPath, @PathVariable String filename) {
        try {
            Path filePath = Paths.get(MEDIA_PATH).resolve(prefixPath).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{prefixPath:.+}/{filename:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String prefixPath, @PathVariable String filename) {
        try {
            Path filePath = Paths.get(MEDIA_PATH).resolve(prefixPath).resolve(filename);
            File file = filePath.toFile();
            if (file.delete()) {
                mediaService.deleteByUrl(prefixPath + "/" + filename);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("unable to delete file");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
