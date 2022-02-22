package com.nfplatform.nfpbackend.smart_transaction.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "smart-transaction"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmartTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "request_key")
    private String requestKey;

    @NotNull
    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "status")
    private String status;
}
