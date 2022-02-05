package com.nfplatform.nfpbackend.piece.service;

import com.nfplatform.nfpbackend.piece.repository.PieceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PieceService {

    private final PieceRepository pieceRepository;
}
