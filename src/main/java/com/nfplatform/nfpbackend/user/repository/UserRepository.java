package com.nfplatform.nfpbackend.user.repository;

import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.user.repository.entity.UserWithOwnerShipCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTokenEquals(String token);
    Optional<User> findByKakaoIdEquals(Long kakoId);

    List<User> findTop15ByOrderByKlayDesc();

    @Query(value = "select u.*, count(o.id) as count_of_ownership from user u " +
            "left outer join ownership o " +
            "on u.id = o.owner_id " +
            "order by count(o.id) DESC " +
            "limit 15", nativeQuery = true)
    List<UserWithOwnerShipCount> findByOrderByOwnershipListDesc();

}
