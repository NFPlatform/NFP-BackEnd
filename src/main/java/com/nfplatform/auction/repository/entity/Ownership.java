package com.nfplatform.auction.repository.entity;

import com.nfplatform.piece.repository.entity.Piece;
import com.nfplatform.user.repository.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "ownership"
)
@Getter
@Setter
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class Ownership {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(targetEntity = Piece.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_id")
    private Piece piece;
}
