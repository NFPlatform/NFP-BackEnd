package com.nfplatform.nfpbackend.piece.repository;

import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Long> {
}
