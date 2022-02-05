package com.nfplatform.auction.repository;

import com.nfplatform.auction.repository.entity.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnershipRepository extends JpaRepository<Ownership, Long> {
}