package com.example.applet.services;

import com.example.applet.entities.*;
import com.example.applet.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final MediaRepository mediaRepository;
    private final FavoriteRepository favoriteRepository;
    private final HistoryRepository historyRepository;

    public ResourceService(ResourceRepository resourceRepository,
                           UserRepository userRepository,
                           MediaRepository mediaRepository,
                           FavoriteRepository favoriteRepository,
                           HistoryRepository historyRepository) {
        this.resourceRepository = resourceRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
        this.historyRepository = historyRepository;
    }

    public Resource resourceBrief(Long id) throws Exception {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isEmpty()) {
            throw new Exception("Resource not found");
        }
        Resource resource = resourceOptional.get();
        Optional<User> userOptional = userRepository.findById(resource.getUserId());
        if (userOptional.isEmpty()) {
            throw new Exception("User not found");
        }
        resource.setUsername(userOptional.get().getName());
        return resource;
    }

    public Resource resourceDetail(Long id) throws Exception {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isEmpty()) {
            throw new Exception("Resource not found");
        }
        Resource resource = resourceOptional.get();
        List<String> mediaUrls = new ArrayList<>();
        for (Media media : mediaRepository.findAllByResourceId(resource.getId())) {
            mediaUrls.add(media.getUrl());
        }
        resource.setMediaUrl(mediaUrls);
        Optional<User> userOptional = userRepository.findById(resource.getUserId());
        if (userOptional.isEmpty()) {
            throw new Exception("User not found");
        }
        resource.setUsername(userOptional.get().getName());
        return resource;
    }

    public Resource getDetail(Long resourceId, Long userId) throws Exception {
        Resource resource = resourceDetail(resourceId);
        resource.setView(resource.getView() + 1);
        resourceRepository.save(resource);
        History history = historyRepository.findByUserIdAndResourceId(userId, resourceId);
        if (history == null) {
            history = new History();
            history.setUserId(userId);
            history.setResourceId(resourceId);
        }
        history.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        historyRepository.save(history);
        return resource;
    }

    public Iterable<Resource> findMyResource(Long userId) {
        return resourceRepository.findAllByUserId(userId);
    }

    public Resource publish(Resource resource) throws Exception {
        if (userRepository.findById(resource.getUserId()).isEmpty()) {
            throw new Exception("User not found");
        }
        resource.setStatus("unchecked");
        return resourceRepository.save(resource);
    }

    public Resource update(Long id, Resource resource) throws Exception {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isEmpty()) {
            throw new Exception("Resource not found");
        }
        Resource oldResource = resourceOptional.get();
        oldResource.setName(resource.getName());
        oldResource.setType(resource.getType());
        oldResource.setUserId(resource.getUserId());
        oldResource.setDescription(resource.getDescription());
        oldResource.setPrice(resource.getPrice());
        oldResource.setView(resource.getView());
        resourceRepository.save(oldResource);
        return oldResource;
    }

    @Transactional
    public void deleteResourceById(Long id) throws Exception {
        if (resourceRepository.findById(id).isEmpty()) {
            throw new Exception("Resource not found");
        }
        favoriteRepository.deleteAllByResourceId(id);
        historyRepository.deleteAllByResourceId(id);
        resourceRepository.deleteById(id);
    }

    public List<Resource> recommendResource() {
        Iterable<Resource> resources = resourceRepository.findTop10ByOrderByViewDesc();
        return (List<Resource>) resources;
    }

    public void favorite(Favorite favorite) throws Exception {
        if (userRepository.findById(favorite.getUserId()).isEmpty()) {
            throw new Exception("User not found");
        }
        if (resourceRepository.findById(favorite.getResourceId()).isEmpty()) {
            throw new Exception("Resource not found");
        }
        favoriteRepository.save(favorite);
    }

    @Transactional
    public void deleteFavorite(Favorite favorite) throws Exception {
        if (userRepository.findById(favorite.getUserId()).isEmpty()) {
            throw new Exception("User not found");
        }
        if (resourceRepository.findById(favorite.getResourceId()).isEmpty()) {
            throw new Exception("Resource not found");
        }
        favoriteRepository.deleteByUserIdAndResourceId(favorite.getUserId(), favorite.getResourceId());
    }

    public List<Resource> getFavoriteList(Long userId) {
        Iterable<Favorite> favorites = favoriteRepository.findAllByUserId(userId);
        List<Resource> resources = new ArrayList<>();
        for (Favorite favorite : favorites) {
            Optional<Resource> resourceOptional = resourceRepository.findById(favorite.getResourceId());
            resourceOptional.ifPresent(resources::add);
        }
        return resources;
    }

    public List<Resource> getHistoryList(Long userId) {
        Iterable<History> histories = historyRepository.findAllByUserIdOrderByUpdatedAtDesc(userId);
        List<Resource> resources = new ArrayList<>();
        for (History history : histories) {
            Optional<Resource> resourceOptional = resourceRepository.findById(history.getResourceId());
            if (resourceOptional.isPresent()) {
                Resource resource = resourceOptional.get();
                resource.setLastViewed(history.getUpdatedAt());
                resources.add(resource);
            }
        }
        return resources;
    }

    public List<Resource> search(String keyword) {
        return (List<Resource>) resourceRepository.findByNameContainingOrDescriptionContainingAndStatus(keyword, keyword, "checked");
    }

    public List<String> searchAutocompleteList(String keyword) {
        List<Resource> resources = search(keyword);
        List<String> result = new ArrayList<>();
        for (Resource resource : resources) {
            result.add(resource.getName());
        }
        return result;
    }

    public List<Resource> toCheck() {
        return (List<Resource>) resourceRepository.findAllByStatus("unchecked");
    }

    public Resource check(Long id, String status) throws Exception {
        Optional<Resource> resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isEmpty()) {
            throw new Exception("Resource not found");
        }
        Resource resource = resourceOptional.get();
        resource.setStatus(status);
        return resourceRepository.save(resource);
    }
}
