package com.nfplatform.artist.controller.dto;

import lombok.*;

@AllArgsConstructor
public class ArtistDTO {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OverAll {
        private int id;
        private String name;
        private String thumbnailImg;
        private int nfpToken;
        private int vote;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteRequest {
        private int artistsId;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private int id;
        private String name;
        private String thumbnailImg;
        private int nfpToken;
        private int vote;

        private String bio;
        private String instagramSrc;

        private int pieceCount;
        private int maxPiecePrice;
        private int totalPiecePrice;
    }
}
