package com.nfplatform.nfpbackend.artist.controller.dto;

import lombok.*;

@AllArgsConstructor
public class ArtistDTO {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Popular {
        private Long id;
        private String name;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OverAll {
        private Long id;
        private String name;
        private Long nfpToken;
        private Long vote;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteRequest {
        private Long artistId;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private Long id;
        private String name;
        private String thumbnailImg;
        private Long nfpToken;
        private Long vote;

        private String bio;
        private String instagramSrc;

        private Long pieceCount;
        private Long maxPiecePrice;
        private Long totalPiecePrice;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Register {
        private String name;
        private String instagramId;
        private String bio;
    }
}
