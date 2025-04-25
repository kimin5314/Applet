package com.example.applet.repositories;

import com.example.applet.entities.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    Iterable<Resource> findAllByUserId(Long userId);

    Iterable<Resource> findTop10ByOrderByViewDesc();

    Iterable<Resource> findByNameContainingOrDescriptionContainingAndStatus(String name, String description, String status);

    Iterable<Resource> findAllByStatus(String status);
}
