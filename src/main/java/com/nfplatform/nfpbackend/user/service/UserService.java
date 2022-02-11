package com.nfplatform.nfpbackend.user.service;

import com.nfplatform.nfpbackend.artist.repository.ArtistRepository;
import com.nfplatform.nfpbackend.user.controller.dto.KakaoDTO;
import com.nfplatform.nfpbackend.user.controller.dto.UserDTO;
import com.nfplatform.nfpbackend.user.model.UserMapper;
import com.nfplatform.nfpbackend.user.repository.UserRepository;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String BASE_PATH = "/var/lib/nfplatform/user";

    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;

    private final KakaoService kakaoService;

    @Transactional
    public void register(UserDTO.RegisterRequest registerRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByTokenEquals(registerRequest.getToken());
        if (optionalUser.isPresent()) {
            return;
        }

        User user = User.builder()
                .token(registerRequest.getToken())
                .contractAccountHex(registerRequest.getContractAccountHex())
                .name(registerRequest.getName())
                .klay(0L)
                .build();

        userRepository.save(user);
    }

    @Transactional
    public void login(UserDTO.LoginRequest loginRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByTokenEquals(loginRequest.getToken());
        User user;
        if (!optionalUser.isPresent()) {
            KakaoDTO.GetAccessTokenResponse getAccessTokenResponse = kakaoService.getToken(loginRequest.getToken());
            KakaoDTO.GetKakaoUserResponse getKakaoUserResponse = kakaoService.getUserInfo(getAccessTokenResponse.getAccess_token());
            Optional<User> optionalUser2 = userRepository.findByKakaoIdEquals(getKakaoUserResponse.getId());
            if (optionalUser2.isPresent()) {
                user = optionalUser2.get();
                user.setToken(loginRequest.getToken());
            } else {
                user = User.builder()
                        .kakaoId(getKakaoUserResponse.getId())
                        .token(loginRequest.getToken())
                        .name("Unknown")
                        .klay(0L)
                        .build();
            }
            userRepository.save(user);
        }
    }

    public UserDTO.UserInfo getUserInfo(User user) throws Exception {
        UserDTO.UserInfo userInfo = UserDTO.UserInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .isArtist(false)
                .build();

        if (artistRepository.findByUserEquals(user).isPresent()) {
            userInfo.setArtist(true);
        }

        return userInfo;
    }

    public User getUserFromToken(String token) throws Exception {
        return userRepository.findByTokenEquals(token)
                .orElseThrow(Exception::new);
    }

    public List<UserDTO.TopCollector> getTopCollector() throws Exception {
        List<User> userList = userRepository.findTop15ByOrderByKlayDesc();
        return userList.stream()
                .map(UserMapper::entityToTopCollector)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getUserImg(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(Exception::new);

        if (!user.isSetImg()) {
            ClassPathResource classPathResource = new ClassPathResource("nfp_logo.png");
            Resource resource = new InputStreamResource(classPathResource.getInputStream());

            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } else {
            Path piecePath = Paths.get(UserService.BASE_PATH + "/" + userId);
            String contentType = Files.probeContentType(piecePath);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            Resource resource = new InputStreamResource(Files.newInputStream(piecePath));
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }
    }
}
