package com.nfplatform.nfpbackend.auction.repository;

import com.nfplatform.nfpbackend.auction.repository.entity.Auction;
import com.nfplatform.nfpbackend.auction.repository.entity.Category;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @EntityGraph(attributePaths = {"piece", "seller", "piece.artist", "piece.artist.user"})
    @Query("select a from Auction a where a.id = ?1")
    Optional<Auction> findByIdEqualsWithPieceAndUser(Long id);

    @EntityGraph(attributePaths = {"piece", "seller", "piece.artist"})
    @Query("select a from Auction a where a.status = ?1")
    List<Auction> findByStatusEquals(String status, Sort sort);

    @EntityGraph(attributePaths = {"piece", "seller", "piece.artist"})
    List<Auction> findByStatusEqualsAndPiece_CategoryEquals(String status, Category category, Sort sort);

    @EntityGraph(attributePaths = {"piece", "seller", "piece.artist"})
    List<Auction> findByStatusEqualsAndSellerEquals(String status, User seller);

}
