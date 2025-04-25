package com.example.applet.repositories;


import com.example.applet.entities.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Long> {
    Iterable<History> findAllByUserIdOrderByUpdatedAtDesc(Long userId);

    void deleteAllByResourceId(Long resourceId);

    History findByUserIdAndResourceId(Long userId, Long resourceId);
}
