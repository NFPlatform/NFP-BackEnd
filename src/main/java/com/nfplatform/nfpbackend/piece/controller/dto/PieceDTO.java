package com.nfplatform.nfpbackend.piece.controller.dto;

import lombok.*;

@AllArgsConstructor
public class PieceDTO {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OverAll {
        private Long id;
        private String name;
        private String thumbnailImg;
        private Long nfpToken;
        private Long vote;
    }
}
