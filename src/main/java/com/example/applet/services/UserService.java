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

    public User login(String phone, String role, String openid, String unionid) {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        if (userOptional.isPresent() && userOptional.get().getRole().equals(role)) {
            return userOptional.get();
        } else {
            User user = new User(phone, role, openid, unionid);
            userRepository.save(user);
            return user;
        }
    }
    
    /**
     * Register or update a WeChat user
     */
    public User registerWeixinUser(User user) throws Exception {
        // First check if user with this openid already exists
        Optional<User> existingUserByOpenid = userRepository.findByOpenid(user.getOpenid());
        
        if (existingUserByOpenid.isPresent()) {
            // Update existing user
            User existingUser = existingUserByOpenid.get();
            
            // Only update fields that are not null
            if (user.getName() != null) {
                existingUser.setName(user.getName());
            }
            if (user.getPhone() != null) {
                existingUser.setPhone(user.getPhone());
            }
            if (user.getAvatar() != null) {
                existingUser.setAvatar(user.getAvatar());
            }
            if (user.getRole() != null) {
                existingUser.setRole(user.getRole());
            }
            if (user.getUnionid() != null) {
                existingUser.setUnionid(user.getUnionid());
            }
            
            return userRepository.save(existingUser);
        } else {
            // Create new user
            // Set default values if not provided
            if (user.getRole() == null) {
                user.setRole("user");
            }
            if (user.getAvatar() == null) {
                user.setAvatar("default");
            }
            if (user.getName() == null && user.getPhone() != null) {
                user.setName("user" + user.getPhone());
            } else if (user.getName() == null) {
                user.setName("user" + System.currentTimeMillis());
            }
            
            return userRepository.save(user);
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

    public User findByOpenid(String openid) {
        Optional<User> userOptional = userRepository.findByOpenid(openid);
        return userOptional.orElse(null);
    }

    public User findByUnionid(String unionid) {
        Optional<User> userOptional = userRepository.findByUnionid(unionid);
        return userOptional.orElse(null);
    }
}
