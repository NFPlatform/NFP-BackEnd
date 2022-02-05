package com.nfplatform.nfpbackend.artist.service;

import com.nfplatform.nfpbackend.artist.controller.dto.ArtistDTO;
import com.nfplatform.nfpbackend.artist.model.ArtistMapper;
import com.nfplatform.nfpbackend.artist.repository.ArtistRepository;
import com.nfplatform.nfpbackend.artist.repository.entity.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<ArtistDTO.OverAll> getPopularArtists() {
        List<Artist> artistList = artistRepository.findTop10ByOrderByVoteDesc();
        return artistList.stream()
                .map(ArtistMapper::entityToOverAll)
                .collect(Collectors.toList());
    }

    public

    @Transactional
    public void voteToArtist(ArtistDTO.VoteRequest voteRequest) throws Exception {
        long artistId = voteRequest.getArtistId();
        Artist artist = artistRepository.findById(artistId).orElseThrow(Exception::new);

        artist.setVote(artist.getVote() + 1);
        artistRepository.save(artist);
    }

    @Transactional
    public void deVoteToArtist(ArtistDTO.VoteRequest voteRequest) throws Exception {
        long artistId = voteRequest.getArtistId();
        Artist artist = artistRepository.findById(artistId).orElseThrow(Exception::new);

        artist.setVote(artist.getVote() - 1);
        artistRepository.save(artist);
    }

}
