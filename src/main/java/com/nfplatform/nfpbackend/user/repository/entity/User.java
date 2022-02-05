package com.nfplatform.nfpbackend.user.repository.entity;

import com.nfplatform.nfpbackend.auction.repository.entity.Ownership;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(
        name = "user"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "kakao_token")
    private String kakaoToken;

    @NotNull
    @Column(name = "contract_account_hex")
    private String contractAccountHex;

    @OneToMany(targetEntity = Ownership.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private List<Ownership> ownershipList;
}
