package com.nfplatform.nfpbackend.piece.controller;

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

    @PostMapping("/upload")
    public void uploadPiece(@ParseUser User user,
                            @RequestPart("img") MultipartFile img,
                            @RequestPart("category") String category,
                            @RequestPart("klay") long klay,
                            @RequestPart("title") String title,
                            @RequestPart("bio") String bio,
                            @RequestPart("subLink") String subLink,
                            @RequestPart("contractHex") String contractHex) throws Exception {
        pieceService.uploadPiece(user, img, category, klay, title, bio, subLink, contractHex);
    }

    @GetMapping("/{pieceId}/img")
    public ResponseEntity<?> getPieceImg(@PathVariable(value = "pieceId") Long pieceId) throws Exception {
        return pieceService.getPieceImg(pieceId);
    }
}
