package com.example.applet.repositories;


import com.example.applet.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long>{
    Iterable<Message> findAllBySenderIdAndReceiverId(Long userId1, Long userId2);

    Iterable<Message> findAllByReceiverIdAndSenderId(Long userId1, Long userId2);
}
