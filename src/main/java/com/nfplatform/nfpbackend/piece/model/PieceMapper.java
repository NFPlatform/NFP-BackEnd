package com.nfplatform.nfpbackend.piece.model;

import com.nfplatform.nfpbackend.auction.controller.dto.AuctionDTO;
import com.nfplatform.nfpbackend.piece.controller.dto.PieceDTO;
import com.nfplatform.nfpbackend.piece.repository.entity.Piece;

public class PieceMapper {

    public static PieceDTO.Detail entityToDetail(Piece piece) {
        return PieceDTO.Detail.builder()
                .id(piece.getId())
                .name(piece.getTitle())
                .artist(AuctionDTO.UserBadge.builder()
                        .id(piece.getArtist().getUser().getId())
                        .name(piece.getArtist().getName())
                        .build())
                .vote(piece.getVote())
                .title(piece.getTitle())
                .bio(piece.getBio())
                .subLink(piece.getSubLink())
                .build();
    }

    public static AuctionDTO.Detail entityToAuctionDetail(Piece piece) {
        return AuctionDTO.Detail.builder()
                .id(piece.getId())
                .piece(PieceMapper.entityToDetail(piece))
                .build();
    }
}
