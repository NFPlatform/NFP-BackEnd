package com.nfplatform.piece.repository;

import com.nfplatform.piece.repository.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Long> {
}
