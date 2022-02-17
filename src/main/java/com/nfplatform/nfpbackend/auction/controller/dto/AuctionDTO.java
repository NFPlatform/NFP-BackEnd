package com.nfplatform.nfpbackend.auction.controller.dto;

import com.nfplatform.nfpbackend.piece.controller.dto.PieceDTO;
import lombok.*;

@AllArgsConstructor
public class AuctionDTO {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private Long id;
        private PieceDTO.Detail piece;
        private UserBadge seller;
        private Long klay;
        private Long nfpToken;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Piece {
        private Long id;
        private UserBadge artist;
        private Long vote;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserBadge {
        private Long id;
        private String name;
    }
}
