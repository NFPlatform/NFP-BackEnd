package com.nfplatform.nfpbackend.piece.service;

import com.nfplatform.nfpbackend.artist.repository.ArtistRepository;
import com.nfplatform.nfpbackend.artist.repository.entity.Artist;
import com.nfplatform.nfpbackend.auction.AuctionStatus;
import com.nfplatform.nfpbackend.auction.repository.AuctionRepository;
import com.nfplatform.nfpbackend.auction.repository.CategoryRepository;
import com.nfplatform.nfpbackend.auction.repository.OwnershipRepository;
import com.nfplatform.nfpbackend.auction.repository.entity.Auction;
import com.nfplatform.nfpbackend.auction.repository.entity.Category;
import com.nfplatform.nfpbackend.auction.repository.entity.Ownership;
import com.nfplatform.nfpbackend.piece.repository.PieceRepository;
import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PieceService {

    private static final String BASE_PATH = "/var/lib/nfplatform/piece";

    private final CategoryRepository categoryRepository;
    private final ArtistRepository artistRepository;
    private final PieceRepository pieceRepository;
    private final OwnershipRepository ownershipRepository;
    private final AuctionRepository auctionRepository;


    @Transactional
    public void uploadPiece(User user, MultipartFile img, String categoryName, long klay,
                            String title, String bio, String subLink, String contractHex) throws Exception {

        Artist artist = artistRepository.findByUserEquals(user)
                .orElseThrow(Exception::new);
        Category category = categoryRepository.findByNameEquals(categoryName)
                .orElseThrow(Exception::new);

        Piece newPiece = Piece.builder()
                .contractHex(contractHex)
                .artist(artist)
                .vote(0L)
                .build();

        Ownership ownership = Ownership.builder()
                .owner(user)
                .piece(newPiece)
                .build();

        Auction auction = Auction.builder()
                .piece(newPiece)
                .seller(user)
                .klay(klay)
                .nfpt(0L)
                .title(title)
                .bio(bio)
                .subLink(subLink)
                .status(AuctionStatus.SELL.toString())
                .category(category)
                .build();

        newPiece = pieceRepository.save(newPiece);
        ownershipRepository.save(ownership);
        auctionRepository.save(auction);

        String piecePath = PieceService.BASE_PATH + "/" + newPiece.getId();
        img.transferTo(Paths.get(piecePath));

    }

    public ResponseEntity<?> getPieceImg(Long pieceId) throws Exception {
        if (pieceRepository.existsById(pieceId) == false) {
            throw new Exception();
        }

        Path piecePath = Paths.get(PieceService.BASE_PATH + "/" + pieceId);
        String contentType = Files.probeContentType(piecePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(piecePath));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
