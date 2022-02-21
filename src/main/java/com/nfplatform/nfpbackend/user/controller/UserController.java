package com.nfplatform.nfpbackend.user.controller;

import com.nfplatform.nfpbackend.auction.controller.dto.AuctionDTO;
import com.nfplatform.nfpbackend.piece.controller.dto.PieceDTO;
import com.nfplatform.nfpbackend.security.annotation.ParseUser;
import com.nfplatform.nfpbackend.user.controller.dto.UserDTO;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public void login(@RequestBody UserDTO.LoginRequest loginRequest) throws Exception {
        userService.login(loginRequest);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDTO.RegisterRequest registerRequest) throws Exception {
        userService.register(registerRequest);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@ParseUser User user) throws Exception {
        if (user.getId() == -1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("FORBIDDEN");
        }
        return ResponseEntity.ok(userService.getUserInfo(user));
    }

    @GetMapping("/top")
    public List<UserDTO.TopCollector> getTopCollector() throws Exception {
        return userService.getTopCollector();
    }

    @GetMapping("/{userId}/img")
    public ResponseEntity<?> getUserImg(@PathVariable(value = "userId") Long userId) throws Exception {
        return userService.getUserImg(userId);
    }

    @GetMapping("/piece/sell")
    public List<AuctionDTO.Detail> getMySellingPieces(@ParseUser User user) throws Exception {
        return userService.getMySellingPieces(user);
    }

    @GetMapping("/piece/owned")
    public List<PieceDTO.Detail> getMyOwnedPieces(@ParseUser User user) throws Exception {
        return userService.getMyOwnedPieces(user);
    }
}
