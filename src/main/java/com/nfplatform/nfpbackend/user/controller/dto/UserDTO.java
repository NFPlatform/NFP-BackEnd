package com.nfplatform.nfpbackend.user.controller.dto;

import lombok.*;

@AllArgsConstructor
public class UserDTO {

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        private String token;
        private String contractAccountHex;
        private String name;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String token;
        private boolean local;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopCollector {
        private Long id;
        private String name;
        private Long klay;
        private int countOfPiece;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private Long id;
        private String name;
        private Long nfpt;
        private boolean isArtist;
    }

}
