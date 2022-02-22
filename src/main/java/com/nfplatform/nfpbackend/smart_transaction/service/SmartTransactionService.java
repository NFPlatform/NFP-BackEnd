package com.nfplatform.nfpbackend.smart_transaction.service;

import com.nfplatform.nfpbackend.auction.service.AuctionService;
import com.nfplatform.nfpbackend.piece.service.PieceService;
import com.nfplatform.nfpbackend.smart_transaction.controller.dto.SmartTransactionDto;
import com.nfplatform.nfpbackend.smart_transaction.repository.SmartTransactionRepository;
import com.nfplatform.nfpbackend.smart_transaction.repository.entity.SmartTransaction;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SmartTransactionService {

    private final AuctionService auctionService;
    private final PieceService pieceService;

    private final SmartTransactionRepository smartTransactionRepository;

    public void validateBuyTransaction(User user, Long auctionId, SmartTransactionDto.KlipPrepareResponse klipPrepareResponse) throws Exception {
        this.auctionService.validateBuyPiece(user, auctionId);

        SmartTransaction smartTransaction = SmartTransaction.builder()
                .requestKey(klipPrepareResponse.getRequestKey())
                .expirationTime(LocalDateTime.now().plusSeconds(klipPrepareResponse.getTimestamp()))
                .type("BUY_NFT")
                .status("Waiting")
                .build();
        smartTransactionRepository.save(smartTransaction);
    }

    public void validateUploadTransaction(User user, SmartTransactionDto.UploadPieceValidate uploadPieceValidate) throws Exception {
        this.pieceService.validateUploadPiece(user, uploadPieceValidate);

        SmartTransaction smartTransaction = SmartTransaction.builder()
                .requestKey(uploadPieceValidate.getRequestKey())
                .expirationTime(LocalDateTime.now().plusSeconds(uploadPieceValidate.getTimestamp()))
                .type("MINT_WITH_TOKEN_URI")
                .status("Waiting")
                .build();
        smartTransactionRepository.save(smartTransaction);
    }

    public void validateSellTransaction(User user, SmartTransactionDto.SetToSellingValidate setToSellingReq) throws Exception {
        this.pieceService.validateSeToSelling(user, setToSellingReq);

        SmartTransaction smartTransaction = SmartTransaction.builder()
                .requestKey(setToSellingReq.getRequestKey())
                .expirationTime(LocalDateTime.now().plusSeconds(setToSellingReq.getTimestamp()))
                .type("SAFE_TRANSFER_FROM")
                .status("Waiting")
                .build();
        smartTransactionRepository.save(smartTransaction);
    }

    @Transactional
    public void validateRequestKey(String requestKey, String type) throws Exception {
        SmartTransaction smartTransaction = smartTransactionRepository.findByRequestKeyEqualsAndTypeEqualsAndStatusEquals(requestKey, type, "Waiting")
                .orElseThrow(Exception::new);

        if (smartTransaction.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new Exception();
        }

        smartTransaction.setStatus("Done");
        smartTransactionRepository.save(smartTransaction);
    }

}
