package com.nfplatform.nfpbackend.vote.service;

import com.nfplatform.nfpbackend.piece.repository.PieceRepository;
import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.vote.repository.VoteRepository;
import com.nfplatform.nfpbackend.vote.repository.entity.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final PieceRepository pieceRepository;
    private final VoteRepository voteRepository;

    @Transactional
    public void voteToPiece(User user, Long pieceId) throws Exception {
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(Exception::new);

        if (voteRepository.existsByUserEqualsAndPieceEquals(user, piece)) {
            throw new Exception();
        }

        Vote vote = Vote.builder()
                .user(user)
                .piece(piece)
                .build();
        voteRepository.save(vote);
    }

    @Transactional
    public void deVoteToPiece(User user, Long pieceId) throws Exception {
        Piece piece = pieceRepository.findById(pieceId)
                .orElseThrow(Exception::new);

        Vote vote = voteRepository.findByUserEqualsAndPieceEquals(user, piece)
                .orElseThrow(Exception::new);

        voteRepository.delete(vote);
    }

    public Long countVoteOfPiece(Piece piece) throws Exception {
        return voteRepository.countByPieceEquals(piece);
    }

    public boolean checkIfVote(User user, Piece piece) throws Exception {
        return voteRepository.existsByUserEqualsAndPieceEquals(user, piece);
    }
}
