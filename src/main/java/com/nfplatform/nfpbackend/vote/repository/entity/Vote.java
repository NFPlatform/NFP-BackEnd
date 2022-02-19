package com.nfplatform.nfpbackend.vote.repository.entity;

import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(
        name = "vote"
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Piece.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_id")
    private Piece piece;
}
