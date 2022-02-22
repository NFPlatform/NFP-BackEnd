package com.nfplatform.nfpbackend.piece.controller;

import com.nfplatform.nfpbackend.piece.controller.dto.PieceDTO;
import com.nfplatform.nfpbackend.piece.service.PieceService;
import com.nfplatform.nfpbackend.security.annotation.ParseUser;
import com.nfplatform.nfpbackend.smart_transaction.service.SmartTransactionService;
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
    private final SmartTransactionService smartTransactionService;

    @PostMapping("/register")
    public PieceDTO.RegisterResponse uploadPiece(@ParseUser User user,
                                                 @RequestPart("img") MultipartFile img,
                                                 @RequestPart("registerForm") PieceDTO.RegisterRequest registerRequest,
                                                 @RequestParam(name = "requestKey") String requestKey) throws Exception {
        smartTransactionService.validateRequestKey(requestKey, "MINT_WITH_TOKEN_URI");
        return pieceService.uploadPiece(user, img, registerRequest);
    }

    @GetMapping("/{pieceId}/img")
    public ResponseEntity<?> getPieceImg(@PathVariable(value = "pieceId") Long pieceId) throws Exception {
        return pieceService.getPieceImg(pieceId);
    }

    @PostMapping("/sell")
    public PieceDTO.SetToSellingRes setToSelling(@ParseUser User user,
                                                 @RequestBody PieceDTO.SetToSellingReq setToSellingReq,
                                                 @RequestParam(name = "requestKey") String requestKey) throws Exception {
        smartTransactionService.validateRequestKey(requestKey, "SAFE_TRANSFER_FROM");
        return pieceService.setToSelling(user, setToSellingReq);
    }
}
