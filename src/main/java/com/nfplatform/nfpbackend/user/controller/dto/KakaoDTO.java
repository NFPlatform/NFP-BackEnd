package com.nfplatform.nfpbackend.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class KakaoDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetAccessTokenResponse {
        private String token_type;
        private String access_token;
        private int expires_in;
        private String refresh_token;
        private int refresh_token_expires_in;
        private String scope;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetKakaoUserResponse {
        private long id;
        private String connected_at;
        private KakaoProperties properties;
        private KakaoAccount kakao_account;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KakaoProperties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KakaoAccount {
        private boolean profile_nickname_needs_agreement;
        private boolean profile_image_needs_agreement;
        private KakaoProfile profile;
        private boolean has_email;
        private boolean email_needs_agreement;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KakaoProfile {
        private String nickname;
        private String thumbnail_image_url;
        private String profile_image_url;
        private boolean is_default_image;
    }

}
