package com.nfplatform.nfpbackend.auction.model;

import com.nfplatform.nfpbackend.auction.controller.dto.AuctionDTO;
import com.nfplatform.nfpbackend.auction.repository.entity.Auction;
import com.nfplatform.nfpbackend.piece.repository.entity.Piece;

public class AuctionMapper {

    public static AuctionDTO.Detail entityToDetail(Auction auction) {
        return AuctionDTO.Detail.builder()
                .id(auction.getId())
                .name(auction.getTitle())
                .piece(AuctionMapper.entityToAuctionPieceDTO(auction.getPiece()))
                .seller(AuctionDTO.UserBadge.builder()
                        .id(auction.getSeller().getId())
                        .name(auction.getSeller().getName())
                        .build())
                .klay(auction.getKlay())
                .nfpToken(auction.getNfpt())
                .build();
    }

    public static AuctionDTO.Piece entityToAuctionPieceDTO(Piece piece) {
        return AuctionDTO.Piece.builder()
                .id(piece.getId())
                .artist(AuctionDTO.UserBadge.builder()
                        .id(piece.getArtist().getId())
                        .name(piece.getArtist().getName())
                        .build())
                .vote(piece.getVote())
                .build();
    }
}
