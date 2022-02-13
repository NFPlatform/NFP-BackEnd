package com.nfplatform.nfpbackend.artist.service;

import com.nfplatform.nfpbackend.artist.controller.dto.ArtistDTO;
import com.nfplatform.nfpbackend.artist.model.ArtistMapper;
import com.nfplatform.nfpbackend.artist.repository.ArtistRepository;
import com.nfplatform.nfpbackend.artist.repository.entity.Artist;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistDTO.Popular getPopularArtist() {
        Optional<Artist> optionalArtist = artistRepository.findFirstByOrderByVoteDesc();
        if (optionalArtist.isPresent()) {
            Artist artist = optionalArtist.get();
            return ArtistDTO.Popular.builder()
                    .id(artist.getId())
                    .name(artist.getName())
                    .build();
        } else {
            return ArtistDTO.Popular.builder()
                    .id(-1L)
                    .name("-")
                    .build();
        }
    }

    public List<ArtistDTO.OverAll> getPopularArtists() {
        List<Artist> artistList = artistRepository.findTop10ByOrderByVoteDesc();
        return artistList.stream()
                .map(ArtistMapper::entityToOverAll)
                .collect(Collectors.toList());
    }

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

    public void registerArtist(User user, ArtistDTO.Register registerRequest) throws Exception {
        Artist artist = Artist.builder()
                .name(registerRequest.getName())
                .user(user)
                .vote(0L)
                .bio(registerRequest.getBio())
                .instagram(registerRequest.getInstagramId())
                .pieceCount(0L)
                .maxPiecePrice(0L)
                .totalPiecePrice(0L)
                .build();
        artistRepository.save(artist);
    }

}
