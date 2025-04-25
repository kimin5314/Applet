package com.example.applet.repositories;

import com.example.applet.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByResourceId(Long resourceId);

    Iterable<Comment> findAllByParentId(Long parentId);

    void deleteAllByResourceId(Long resourceId);

    void deleteAllByParentId(Long parentId);
}
