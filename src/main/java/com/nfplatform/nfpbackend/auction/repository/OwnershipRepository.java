package com.nfplatform.nfpbackend.auction.repository;

import com.nfplatform.nfpbackend.auction.repository.entity.Ownership;
import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
    @Query("select o from Ownership o where o.piece = ?1 and o.owner = ?2")
    Optional<Ownership> findByPieceEqualsAndOwnerEquals(Piece piece, User owner);

}