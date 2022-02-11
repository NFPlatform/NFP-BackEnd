package com.nfplatform.nfpbackend.artist.model;

import com.nfplatform.nfpbackend.artist.controller.dto.ArtistDTO;
import com.nfplatform.nfpbackend.artist.repository.entity.Artist;

public class ArtistMapper {

    public static ArtistDTO.OverAll entityToOverAll(Artist artist) {
        return ArtistDTO.OverAll.builder()
                .id(artist.getId())
                .name(artist.getName())
                .nfpToken(artist.getUser().getKlay())
                .vote(artist.getVote())
                .build();
    }

    public static ArtistDTO.Detail entityToDetail(Artist artist) {
        return ArtistDTO.Detail.builder()
                .id(artist.getId())
                .name(artist.getName())
                .nfpToken(artist.getUser().getKlay())
                .vote(artist.getVote())
                .bio(artist.getBio())
                .instagramSrc(artist.getInstagram())
                .pieceCount(artist.getPieceCount())
                .maxPiecePrice(artist.getMaxPiecePrice())
                .totalPiecePrice(artist.getTotalPiecePrice())
                .build();
    }
}
