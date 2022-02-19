package com.nfplatform.nfpbackend.user.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Immutable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWithOwnerShipCount {

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

    @NotNull
    @Column(name = "nftp")
    private Long nftp;

    @Column(name = "count_of_ownership")
    private Integer countOfOwnership;
}
