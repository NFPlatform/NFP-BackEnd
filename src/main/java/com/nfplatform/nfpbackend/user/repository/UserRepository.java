package com.nfplatform.nfpbackend.user.repository;

import com.nfplatform.nfpbackend.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTokenEquals(String token);

    List<User> findTop15ByOrderByKlayDesc();



}
