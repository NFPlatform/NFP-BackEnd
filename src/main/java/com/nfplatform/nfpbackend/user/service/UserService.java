package com.nfplatform.nfpbackend.user.service;

import com.nfplatform.nfpbackend.piece.service.PieceService;
import com.nfplatform.nfpbackend.user.controller.dto.UserDTO;
import com.nfplatform.nfpbackend.user.model.UserMapper;
import com.nfplatform.nfpbackend.user.repository.UserRepository;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URI;
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
    public ResponseEntity<?> login(UserDTO.LoginRequest loginRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByTokenEquals(loginRequest.getToken());
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok("OK");
        } else {
            URI redirectURI = new URI("http://localhost:8080/register");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(redirectURI);
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        }

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
        if (userRepository.existsById(userId) == false) {
            throw new Exception();
        }

        Path piecePath = Paths.get(UserService.BASE_PATH + "/" + userId);
        String contentType = Files.probeContentType(piecePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(piecePath));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
