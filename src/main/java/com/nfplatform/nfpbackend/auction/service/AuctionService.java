package com.nfplatform.nfpbackend.auction.service;

import com.nfplatform.nfpbackend.auction.AuctionStatus;
import com.nfplatform.nfpbackend.auction.controller.dto.AuctionDTO;
import com.nfplatform.nfpbackend.auction.model.AuctionMapper;
import com.nfplatform.nfpbackend.auction.repository.AuctionRepository;
import com.nfplatform.nfpbackend.auction.repository.CategoryRepository;
import com.nfplatform.nfpbackend.auction.repository.OwnershipRepository;
import com.nfplatform.nfpbackend.auction.repository.entity.Auction;
import com.nfplatform.nfpbackend.auction.repository.entity.Category;
import com.nfplatform.nfpbackend.auction.repository.entity.Ownership;
import com.nfplatform.nfpbackend.piece.repository.PieceRepository;
import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.UserRepository;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import com.nfplatform.nfpbackend.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final OwnershipRepository ownershipRepository;
    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;
    private final PieceRepository pieceRepository;
    private final UserRepository userRepository;

    private final VoteService voteService;

    public List<AuctionDTO.Detail> getAuctionList(String categoryName, Sort sort) throws Exception {
        List<Auction> auctionList;
        if (categoryName.equals("all")) {
            auctionList = auctionRepository.findByStatusEquals(AuctionStatus.SELL.toString(), sort);
        } else {
            Category category = categoryRepository.findByNameEquals(categoryName)
                    .orElseThrow(Exception::new);
            auctionList = auctionRepository.findByStatusEqualsAndPiece_CategoryEquals(AuctionStatus.SELL.toString(), category, sort);
        }

        return auctionList.stream()
                .map(AuctionMapper::entityToDetail)
                .collect(Collectors.toList());
    }

    public AuctionDTO.Detail getAuction(User user, Long auctionId) throws Exception {
        Auction auction = auctionRepository.findByIdEqualsWithPieceAndUser(auctionId)
                .orElseThrow(Exception::new);
        Piece piece = auction.getPiece();

        boolean isVote = voteService.checkIfVote(user, piece);

        AuctionDTO.Detail detail = AuctionMapper.entityToDetail(auction);
        detail.setUserVote(isVote);
        return detail;
    }

    @Transactional
    public void buyPiece(User user, Long auctionId) throws Exception {
        Auction auction = auctionRepository.findByIdEqualsWithPieceAndUser(auctionId)
                .orElseThrow(Exception::new);

        if (auction.getStatus().equals(AuctionStatus.SOLD.toString())) {
            throw new Exception();
        }

        Piece piece = auction.getPiece();
        if (piece.getState().equals("Owned")) {
            throw new Exception();
        }

        if (auction.getSeller().getId() == user.getId()) {
            throw new Exception();
        }

        Long klay = auction.getKlay();
        Long nftp = (long) (klay * 0.05);

        user.setKlay(user.getKlay() + klay);
        user.setNftp(user.getNftp() + nftp);

        User seller = auction.getSeller();
        Ownership sellerOwnership = ownershipRepository.findByPieceEqualsAndOwnerEquals(piece, seller)
                .orElseThrow(Exception::new);

        Ownership newOwnership = Ownership.builder()
                .piece(piece)
                .owner(user)
                .build();

        piece.setState("Owned");
        pieceRepository.save(piece);

        ownershipRepository.delete(sellerOwnership);
        ownershipRepository.save(newOwnership);

        auction.setStatus(AuctionStatus.SOLD.toString());
        auctionRepository.save(auction);

        userRepository.save(user);

        if (piece.getArtist().getUser().getId() != seller.getId()) {
            seller.setKlay(seller.getKlay() - klay);
            userRepository.save(seller);
        }
    }
}
