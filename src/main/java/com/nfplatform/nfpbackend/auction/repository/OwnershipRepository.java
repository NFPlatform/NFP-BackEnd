package com.nfplatform.nfpbackend.auction.repository;

import com.nfplatform.nfpbackend.auction.repository.entity.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
}