package com.nfplatform.nfpbackend.auction.repository.entity;

import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "auction"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Piece.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_id")
    private Piece piece;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @NotNull
    @Column(name = "klay")
    private Long klay;

    @NotNull
    @Column(name = "nfpt")
    private Long nfpt;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "bio")
    private String bio;

    @Column(name = "sub_link")
    private String subLink;

    @NotNull
    @Column(name = "status")
    private String status;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
