package com.example.applet.services;

import org.springframework.stereotype.Service;

import com.example.applet.entities.Message;
import com.example.applet.repositories.MessageRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getDialogue(Long userId1, Long userId2) {
        Iterable<Message> m1 = messageRepository.findAllByReceiverIdAndSenderId(userId1, userId2);
        Iterable<Message> m2 = messageRepository.findAllBySenderIdAndReceiverId(userId1, userId2);
        List<Message> msg = new ArrayList<>();
        for (Message m : m1) {
            msg.add(m);
        }
        for (Message m : m2) {
            msg.add(m);
        }
        msg.sort(Comparator.comparing(Message::getTime));
        return msg;
    }

    public Message send(Long senderId, Long receiverId, String content) {
        Message msg = new Message();
        msg.setSenderId(senderId);
        msg.setReceiverId(receiverId);
        msg.setContent(content);
        return messageRepository.save(msg);
    }

    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }
}
