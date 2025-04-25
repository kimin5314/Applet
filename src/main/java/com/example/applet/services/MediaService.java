package com.example.applet.services;

import com.example.applet.entities.Media;
import com.example.applet.entities.Resource;
import com.example.applet.repositories.ResourceRepository;
import org.springframework.stereotype.Service;

import com.example.applet.repositories.MediaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MediaService {
    private final MediaRepository mediaRepository;

    private final ResourceRepository resourceRepository;

    public MediaService(MediaRepository mediaRepository, ResourceRepository resourceRepository) {
        this.mediaRepository = mediaRepository;
        this.resourceRepository = resourceRepository;
    }

    public void saveMedia(Media media) throws Exception {
        Optional<Resource> resourceOptional = resourceRepository.findById(media.getResourceId());
        if (resourceOptional.isEmpty()) {
            throw new Exception("resource not found");
        }
        Resource resource = resourceOptional.get();
        if (resource.getCover() == null) {
            resource.setCover(media.getUrl());
            resourceRepository.save(resource);
        }
        mediaRepository.save(media);
    }

    @Transactional
    public void deleteAllByResourceId(Long resourceId) {
        mediaRepository.deleteAllByResourceId(resourceId);
    }

    public void deleteByUrl(String url) {
        mediaRepository.deleteByUrl(url);
    }
}
