package com.nfplatform.nfpbackend.vote.controller;

import com.nfplatform.nfpbackend.security.annotation.ParseUser;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{pieceId}")
    public void voteToPiece(@ParseUser User user,
                            @PathVariable(value = "pieceId") Long pieceId) throws Exception {
        voteService.voteToPiece(user, pieceId);
    }

    @DeleteMapping("/{pieceId}")
    public void deVoteToPiece(@ParseUser User user,
                            @PathVariable(value = "pieceId") Long pieceId) throws Exception {
        voteService.deVoteToPiece(user, pieceId);
    }
}
