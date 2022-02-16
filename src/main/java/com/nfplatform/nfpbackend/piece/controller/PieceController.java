package com.nfplatform.nfpbackend.piece.controller;

import com.nfplatform.nfpbackend.piece.controller.dto.PieceDTO;
import com.nfplatform.nfpbackend.piece.service.PieceService;
import com.nfplatform.nfpbackend.security.annotation.ParseUser;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/piece")
public class PieceController {

    private final PieceService pieceService;

    @PostMapping("/register")
    public PieceDTO.RegisterResponse uploadPiece(@ParseUser User user,
                            @RequestPart("img") MultipartFile img,
                            @RequestPart("registerForm") PieceDTO.RegisterRequest registerRequest) throws Exception {
        return pieceService.uploadPiece(user, img, registerRequest);
    }

    @GetMapping("/{pieceId}/img")
    public ResponseEntity<?> getPieceImg(@PathVariable(value = "pieceId") Long pieceId) throws Exception {
        return pieceService.getPieceImg(pieceId);
    }
}
