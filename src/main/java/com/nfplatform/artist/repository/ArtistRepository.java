package com.nfplatform.artist.repository;

import com.nfplatform.artist.repository.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
