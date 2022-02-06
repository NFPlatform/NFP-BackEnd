package com.nfplatform.nfpbackend.auction.repository;

import com.nfplatform.nfpbackend.auction.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameEquals(String name);

}