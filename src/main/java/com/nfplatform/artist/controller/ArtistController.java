package com.nfplatform.artist.controller;

import com.nfplatform.artist.controller.dto.ArtistDTO;
import com.nfplatform.artist.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/popular")
    public List<ArtistDTO.OverAll> getPopularArtists() throws Exception {
        return artistService.getPopularArtists();
    }

    @PostMapping("/vote")
    public void voteToArtists(@RequestBody ArtistDTO.VoteRequest voteRequest) throws Exception {
        artistService.voteToArtist(voteRequest);
    }
}
