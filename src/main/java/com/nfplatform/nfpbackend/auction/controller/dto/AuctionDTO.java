package com.nfplatform.nfpbackend.auction.controller.dto;

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
        private String name;
        private Piece piece;
        private UserBadge owner;
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
