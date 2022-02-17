package com.nfplatform.nfpbackend.piece.controller.dto;

import com.nfplatform.nfpbackend.auction.controller.dto.AuctionDTO;
import lombok.*;

@AllArgsConstructor
public class PieceDTO {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        private String category;
        private String title;
        private String bio;
        private String subLink;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterResponse {
        private long pieceId;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private Long id;
        private String name;
        private AuctionDTO.UserBadge artist;
        private Long vote;
        private String title;
        private String bio;
        private String subLink;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SetToSellingReq {
        private Long pieceId;
        private Long klay;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SetToSellingRes {
        private Long auctionId;
    }
}
