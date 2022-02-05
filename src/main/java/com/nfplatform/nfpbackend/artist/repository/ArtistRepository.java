package com.nfplatform.nfpbackend.artist.repository;

import com.nfplatform.nfpbackend.artist.repository.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findTop10ByOrderByVoteDesc();

}
