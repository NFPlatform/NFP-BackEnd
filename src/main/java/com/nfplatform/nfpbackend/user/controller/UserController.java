package com.nfplatform.nfpbackend.user.controller;

import com.nfplatform.nfpbackend.user.controller.dto.UserDTO;
import com.nfplatform.nfpbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(UserDTO.LoginRequest loginRequest) throws Exception {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public void register(UserDTO.RegisterRequest registerRequest) throws Exception {
        userService.register(registerRequest);
    }

    @GetMapping("/top")
    public List<UserDTO.TopCollector> getTopCollector() throws Exception {
        return userService.getTopCollector();
    }

    @GetMapping("/{userId}/img")
    public ResponseEntity<?> getUserImg(@PathVariable(value = "userId") Long userId) throws Exception {
        return userService.getUserImg(userId);
    }
}
