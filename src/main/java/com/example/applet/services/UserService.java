package com.example.applet.services;

import com.example.applet.entities.User;
import com.example.applet.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String phone, String role) {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isPresent() && userOptional.get().getRole().equals(role)) {
            return userOptional.get();
        } else {
            User user = new User(phone, role);
            userRepository.save(user);
            return user;
        }
    }

    public User update(Long id, User user) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found");
        }
        User oldUser = userOptional.get();
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAvatar(user.getAvatar());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
        return oldUser;
    }
}
