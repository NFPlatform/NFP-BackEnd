package com.nfplatform.nfpbackend.artist.controller;

import com.nfplatform.nfpbackend.artist.controller.dto.ArtistDTO;
import com.nfplatform.nfpbackend.artist.service.ArtistService;
import com.nfplatform.nfpbackend.security.annotation.ParseUser;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/top")
    public ArtistDTO.Popular getTopArtist() throws Exception {
        return artistService.getPopularArtist();
    }

    @GetMapping("/popular")
    public List<ArtistDTO.OverAll> getPopularArtists() throws Exception {
        return artistService.getPopularArtists();
    }

    @PostMapping("/vote")
    public void voteToArtists(@RequestBody ArtistDTO.VoteRequest voteRequest) throws Exception {
        artistService.voteToArtist(voteRequest);
    }

    @PostMapping("/register")
    public void registerArtist(@ParseUser User user, @RequestPart(value = "file") MultipartFile imgFile,
                               @RequestPart(value = "data") ArtistDTO.Register registerRequest) throws Exception{
        artistService.registerArtist(user, registerRequest);
    }
}
