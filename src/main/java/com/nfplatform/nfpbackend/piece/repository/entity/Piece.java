package com.nfplatform.nfpbackend.piece.repository.entity;

import com.nfplatform.nfpbackend.artist.repository.entity.Artist;
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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "contract_hex")
    private String contractHex;

    @ManyToOne(targetEntity = Artist.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @NotNull
    @Column(name = "vote")
    private Long vote;

}
