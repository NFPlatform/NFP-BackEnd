package com.nfplatform.nfpbackend.artist.repository;

import com.nfplatform.nfpbackend.artist.repository.entity.Artist;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findTop10ByOrderByVoteDesc();

    @EntityGraph(attributePaths = {"user"})
    Optional<Artist> findByUserEquals(User user);

}
