package com.nfplatform.nfpbackend.user.service;

import com.nfplatform.nfpbackend.user.controller.dto.KakaoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoService {

    private static final String kakaoOAuthHost = "https://kauth.kakao.com";
    private static final String kakaoAPIHost = "https://kapi.kakao.com";
    private static final String client_id = "be6a45435e2907dfcf8b40d27dd92ffd";

    @Value("${kakao.redirect}")
    private String redirect_uri;

    public KakaoDTO.GetAccessTokenResponse getToken(String authorize_code) throws Exception {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", client_id);
        map.add("redirect_uri", redirect_uri);
        map.add("code", authorize_code);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<KakaoDTO.GetAccessTokenResponse> res = new RestTemplate().postForEntity(
                kakaoOAuthHost+"/oauth/token", request, KakaoDTO.GetAccessTokenResponse.class);
        if (res.getStatusCode() != HttpStatus.OK) {
            throw new Exception();
        }
        return res.getBody();
    }

    public KakaoDTO.GetKakaoUserResponse getUserInfo(String access_token) throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("Authorization", "Bearer " + access_token);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<KakaoDTO.GetKakaoUserResponse> res = new RestTemplate().postForEntity(
                kakaoAPIHost+"/v2/user/me", request, KakaoDTO.GetKakaoUserResponse.class);
        if (res.getStatusCode() != HttpStatus.OK) {
            throw new Exception();
        }
        return res.getBody();
    }

}
