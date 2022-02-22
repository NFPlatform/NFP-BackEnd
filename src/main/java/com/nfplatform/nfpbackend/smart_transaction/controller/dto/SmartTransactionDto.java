package com.nfplatform.nfpbackend.smart_transaction.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class SmartTransactionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KlipPrepareResponse {
        private String requestKey;
        private Integer timestamp;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BuyPieceValidate {
        private String requestKey;
        private Integer timestamp;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadPieceValidate {
        private String requestKey;
        private Integer timestamp;

        private String category;
        private String title;
        private String bio;
        private String subLink;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SetToSellingValidate {
        private String requestKey;
        private Integer timestamp;

        private Long pieceId;
        private Long klay;
    }
}
