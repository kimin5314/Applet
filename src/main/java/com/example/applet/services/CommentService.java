package com.example.applet.services;

import com.example.applet.entities.Comment;
import com.example.applet.entities.User;
import com.example.applet.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ResourceRepository resourceRepository;

    public CommentService(CommentRepository commentRepository,
                          ResourceRepository resourceRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
    }

    public void create(Comment comment) throws Exception {
        if (comment.getParentId() != null) {
            Optional<Comment> parentComment = commentRepository.findById(comment.getParentId());
            if (parentComment.isEmpty()) {
                throw new Exception("Parent comment not found");
            }
        }
        commentRepository.save(comment);
    }

    public List<Comment> getCommentList(Long resourceId) throws Exception {
        if (resourceRepository.findById(resourceId).isEmpty()) {
            throw new Exception("Resource not found");
        }
        Iterable<Comment> comments = commentRepository.findAllByResourceId(resourceId);
        List<Comment> commentsList = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getParentId() != null) {
                continue;
            }
            User user = userRepository.findById(comment.getUserId()).orElse(null);
            if (user == null) {
                throw new Exception("User not found");
            }
            comment.setUsername(user.getName());
            comment.setChildren(new ArrayList<>());
            for (Comment child : commentRepository.findAllByParentId(comment.getId())) {
                user = userRepository.findById(child.getUserId()).orElse(null);
                if (user == null) {
                    throw new Exception("User not found");
                }
                child.setUsername(user.getName());
                comment.getChildren().add(child);
            }
            commentsList.add(comment);
        }
        return commentsList;
    }

    public void delete(Long id) throws Exception {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            throw new Exception("Comment not found");
        }
        commentRepository.deleteAllByParentId(id);
        commentRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllByResourceId(Long resourceId) {
        commentRepository.deleteAllByResourceId(resourceId);
    }
}
