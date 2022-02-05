package com.nfplatform.piece.service;

import com.nfplatform.piece.repository.PieceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PieceService {

    private final PieceRepository pieceRepository;
}
