package com.nfplatform.nfpbackend.auction.model;

import com.nfplatform.nfpbackend.auction.controller.dto.AuctionDTO;
import com.nfplatform.nfpbackend.auction.repository.entity.Auction;
import com.nfplatform.nfpbackend.piece.model.PieceMapper;

public class AuctionMapper {

    public static AuctionDTO.Detail entityToDetail(Auction auction) {
        return AuctionDTO.Detail.builder()
                .id(auction.getId())
                .piece(PieceMapper.entityToDetail(auction.getPiece()))
                .seller(AuctionDTO.UserBadge.builder()
                        .id(auction.getSeller().getId())
                        .name(auction.getSeller().getName())
                        .build())
                .klay(auction.getKlay())
                .nfpToken(auction.getNfpt())
                .isVote(false)
                .build();
    }
}
