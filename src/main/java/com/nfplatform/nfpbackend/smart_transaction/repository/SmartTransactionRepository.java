package com.nfplatform.nfpbackend.smart_transaction.repository;

import com.nfplatform.nfpbackend.smart_transaction.repository.entity.SmartTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmartTransactionRepository extends JpaRepository<SmartTransaction, Long> {
    Optional<SmartTransaction> findByRequestKeyEqualsAndTypeEqualsAndStatusEquals(String requestKey, String type, String status);

}
