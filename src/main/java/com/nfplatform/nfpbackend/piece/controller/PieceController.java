package com.nfplatform.nfpbackend.piece.controller;

import com.nfplatform.nfpbackend.piece.service.PieceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/piece")
public class PieceController {

    private final PieceService pieceService;


}
