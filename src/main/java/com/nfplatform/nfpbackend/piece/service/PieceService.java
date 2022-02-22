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
import com.nfplatform.nfpbackend.piece.controller.dto.PieceDTO;
import com.nfplatform.nfpbackend.piece.repository.PieceRepository;
import com.nfplatform.nfpbackend.piece.repository.entity.Piece;
import com.nfplatform.nfpbackend.smart_transaction.controller.dto.SmartTransactionDto;
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
import java.util.UUID;

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
    public PieceDTO.RegisterResponse uploadPiece(User user, MultipartFile img, PieceDTO.RegisterRequest registerRequest) throws Exception {

        Artist artist = artistRepository.findByUserEquals(user)
                .orElseThrow(Exception::new);
        Category category = categoryRepository.findByNameEquals(registerRequest.getCategory())
                .orElseThrow(Exception::new);

        Piece newPiece = Piece.builder()
                .artist(artist)
                .vote(0L)
                .title(registerRequest.getTitle())
                .bio(registerRequest.getBio())
                .subLink(registerRequest.getBio())
                .category(category)
                .state("Owned")
                .build();

        newPiece = pieceRepository.save(newPiece);

        Ownership ownership = Ownership.builder()
                .owner(user)
                .piece(newPiece)
                .build();

        ownershipRepository.save(ownership);

        String piecePath = PieceService.BASE_PATH + "/" + newPiece.getId();
        img.transferTo(Paths.get(piecePath));

        return PieceDTO.RegisterResponse.builder()
                .pieceId(newPiece.getId())
                .build();
    }

    public void validateUploadPiece(User user, SmartTransactionDto.UploadPieceValidate uploadPieceValidate) throws Exception {
        artistRepository.findByUserEquals(user)
                .orElseThrow(Exception::new);
        categoryRepository.findByNameEquals(uploadPieceValidate.getCategory())
                .orElseThrow(Exception::new);
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

    public PieceDTO.SetToSellingRes setToSelling(User user, PieceDTO.SetToSellingReq setToSellingReq) throws Exception {
        Piece piece = pieceRepository.findById(setToSellingReq.getPieceId())
                .orElseThrow(Exception::new);

        Auction auction = Auction.builder()
                .piece(piece)
                .seller(user)
                .klay(setToSellingReq.getKlay())
                .nfpt((long) (setToSellingReq.getKlay() * 0.05))
                .status("SELL")
                .build();

        piece.setState("Selling");

        auction = auctionRepository.save(auction);
        pieceRepository.save(piece);

        return PieceDTO.SetToSellingRes.builder()
                .auctionId(auction.getId())
                .build();
    }

    public void validateSeToSelling(User user, SmartTransactionDto.SetToSellingValidate setToSellingValidate) throws Exception {
        Piece piece = pieceRepository.findById(setToSellingValidate.getPieceId())
                .orElseThrow(Exception::new);
        if (piece.getState().equals("Selling")) {
            throw new Exception();
        }
        ownershipRepository.findByPieceEqualsAndOwnerEquals(piece, user)
                .orElseThrow(Exception::new);
    }
}
