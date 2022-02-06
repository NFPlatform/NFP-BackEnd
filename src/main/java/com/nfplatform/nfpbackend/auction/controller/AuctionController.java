package com.nfplatform.nfpbackend.auction.controller;

import com.nfplatform.nfpbackend.auction.controller.dto.AuctionDTO;
import com.nfplatform.nfpbackend.auction.service.AuctionService;
import com.nfplatform.nfpbackend.security.annotation.ParseUser;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping("")
    public List<AuctionDTO.Detail> getAuctionList(@RequestParam("category") String category,
                                                  Pageable pageable) throws Exception {
        return auctionService.getAuctionList(category, pageable.getSortOr(Sort.unsorted()));
    }

    @GetMapping("/{auctionId}")
    public AuctionDTO.Detail getAuction(@PathVariable(value = "auctionId") Long auctionId) throws Exception {
        return auctionService.getAuction(auctionId);
    }

    @PostMapping("/{auctionId}/buy")
    public void buyPiece(@ParseUser User user,
                         @PathVariable(value = "auctionId") Long auctionId) throws Exception {
        auctionService.buyPiece(user, auctionId);
    }
}
