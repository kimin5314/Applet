package com.example.applet.controllers;

import com.example.applet.controllers.dto.WeixinLoginRequest;
import com.example.applet.entities.User;
import com.example.applet.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Value("${wxAppid}")
    private String wxAppid;
    @Value("${wxSecret}")
    private String wxSecret;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/wxlogin")
    public ResponseEntity<?> wxLogin(@RequestBody WeixinLoginRequest loginRequest) {
        try {
            // Call WeChat API to get openid and session_key
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + wxAppid +
                        "&secret=" + wxSecret +
                        "&js_code=" + loginRequest.getCode() +
                        "&grant_type=authorization_code";
                        
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            
            // Parse the response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            
            // Check if the response contains error
            if (root.has("errcode") && root.get("errcode").asInt() != 0) {
                return ResponseEntity.badRequest().body("WeChat authentication failed: " + root.get("errmsg").asText());
            }
            
            // Get the openid and session_key from response
            String openid = root.get("openid").asText();
            // Extract session_key but we store it securely, not returning to client
            root.get("session_key").asText(); // We acknowledge it but don't store it in a variable
            
            // Check if unionid exists (only if user has authorized the app)
            String unionid = root.has("unionid") ? root.get("unionid").asText() : null;
            
            // Check if user exists with this openid
            User existingUser = userService.findByOpenid(openid);
            
            Map<String, Object> result = new HashMap<>();
            result.put("openid", openid);
            if (unionid != null) {
                result.put("unionid", unionid);
            }
            
            if (existingUser != null) {
                // User exists, return user info
                result.put("user", existingUser);
                result.put("isNewUser", false);
            } else {
                // User doesn't exist, only return openid and unionid
                result.put("isNewUser", true);
            }
            
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody WeixinLoginRequest loginRequest) {
        try {
            // Call WeChat API to get openid and session_key
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + wxAppid +
                        "&secret=" + wxSecret +
                        "&js_code=" + loginRequest.getCode() +
                        "&grant_type=authorization_code";
                        
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            
            // Parse the response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            
            // Check if the response contains error
            if (root.has("errcode") && root.get("errcode").asInt() != 0) {
                return ResponseEntity.badRequest().body("WeChat authentication failed: " + root.get("errmsg").asText());
            }
            
            // Get the openid and session_key from response
            String openid = root.get("openid").asText();
            
            // Check if unionid exists (only if user has authorized the app)
            String unionid = root.has("unionid") ? root.get("unionid").asText() : null;
            
            // Get user information from request
            User user = loginRequest.getUser();
            if (user == null) {
                user = new User();
            }
            
            // Set openid and unionid from WeChat API
            user.setOpenid(openid);
            if (unionid != null) {
                user.setUnionid(unionid);
            }
            
            // Check role and set default if not provided
            if (user.getRole() == null) {
                user.setRole("user");
            }
            
            // Create or update user
            User registeredUser = userService.registerWeixinUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("Please login first");
            }
            return ResponseEntity.ok(userService.update(id, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/info/{openid}")
    public ResponseEntity<?> getUserInfo(@PathVariable String openid) {
        try {
            User user = userService.findByOpenid(openid);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
