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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "kakao_id")
    private Long kakaoId;

    @NotNull
    @Column(name = "token")
    private String token;

    @Column(name = "contract_account_hex")
    private String contractAccountHex;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "klay")
    private Long klay;

    @NotNull
    @Column(name = "set_img")
    private boolean setImg;

    @Column(name = "nftp")
    private Long nftp;

    @OneToMany(targetEntity = Ownership.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private List<Ownership> ownershipList;
}
