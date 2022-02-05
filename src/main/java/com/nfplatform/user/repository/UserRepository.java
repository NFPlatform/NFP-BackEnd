package com.nfplatform.user.repository;

import com.nfplatform.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
