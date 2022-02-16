package com.nfplatform.nfpbackend.piece.controller.dto;

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
        private long klay;
        private String subLink;
        private String contractHex;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterResponse {
        private long pieceId;
    }
}
