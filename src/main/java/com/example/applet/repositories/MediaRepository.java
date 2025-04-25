package com.example.applet.repositories;

import com.example.applet.entities.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {
    void deleteAllByResourceId(Long resourceID);

    void deleteByUrl(String url);

    Iterable<Media> findAllByResourceId(Long resourceId);
}
