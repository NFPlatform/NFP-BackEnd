package com.nfplatform.nfpbackend.artist.repository.entity;

import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(
        name = "artist"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "thumbnail_img")
    private String thumbnailImg;

    @NotNull
    @Column(name = "nfp_token")
    private Long nfpToken;

    @NotNull
    @Column(name = "vote")
    private Long vote;

    @Column(name = "bio")
    private String bio;

    @Column(name = "instagram")
    private String instagram;

    @NotNull
    @Column(name = "piece_count")
    private Long pieceCount;

    @NotNull
    @Column(name = "max_piece_price")
    private Long maxPiecePrice;

    @NotNull
    @Column(name = "total_piece_price")
    private Long totalPiecePrice;

    @OneToMany(targetEntity = Piece.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private List<Piece> pieceList;
}
