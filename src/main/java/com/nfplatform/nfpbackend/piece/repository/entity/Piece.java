package com.nfplatform.nfpbackend.piece.repository.entity;

import com.nfplatform.nfpbackend.artist.repository.entity.Artist;
import com.nfplatform.nfpbackend.auction.repository.entity.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "piece"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Artist.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @NotNull
    @Column(name = "vote")
    private Long vote;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "bio")
    private String bio;

    @Column(name = "sub_link")
    private String subLink;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Column(name = "state")
    private String state;

}
