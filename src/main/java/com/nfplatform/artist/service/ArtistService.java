package com.nfplatform.artist.service;

import com.nfplatform.artist.controller.dto.ArtistDTO;
import com.nfplatform.artist.repository.ArtistRepository;
import com.nfplatform.artist.repository.entity.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<ArtistDTO.OverAll> getPopularArtists() {
        return new ArrayList<>();
    }

    @Transactional
    public void voteToArtist(ArtistDTO.VoteRequest voteRequest) throws Exception {
        int artistId = voteRequest.getArtistsId();
        Artist artist = artistRepository.findById((long) artistId).orElseThrow(Exception::new);

        artist.setVote(artist.getVote() + 1);
        artistRepository.save(artist);
    }

    @Transactional
    public void deVoteToArtist(ArtistDTO.VoteRequest voteRequest) throws Exception {
        int artistId = voteRequest.getArtistsId();
        Artist artist = artistRepository.findById((long) artistId).orElseThrow(Exception::new);

        artist.setVote(artist.getVote() - 1);
        artistRepository.save(artist);
    }

}
