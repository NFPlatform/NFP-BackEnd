package com.nfplatform.nfpbackend.vote.repository;

import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.vote.repository.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByUserEqualsAndPieceEquals(User user, Piece piece);

    Optional<Vote> findByUserEqualsAndPieceEquals(User user, Piece piece);

    long countByPieceEquals(Piece piece);



}