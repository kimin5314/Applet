package com.example.applet.repositories;

import com.example.applet.entities.Favorite;
import org.springframework.data.repository.CrudRepository;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    void deleteByUserIdAndResourceId(Long userId, Long resourceId);

    void deleteAllByResourceId(Long resourceId);

    Iterable<Favorite> findAllByUserId(Long userId);
}
