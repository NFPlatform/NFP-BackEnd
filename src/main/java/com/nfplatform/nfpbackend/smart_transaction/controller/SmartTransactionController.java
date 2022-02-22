package com.nfplatform.nfpbackend.smart_transaction.controller;

import com.nfplatform.nfpbackend.piece.controller.dto.PieceDTO;
import com.nfplatform.nfpbackend.security.annotation.ParseUser;
import com.nfplatform.nfpbackend.smart_transaction.controller.dto.SmartTransactionDto;
import com.nfplatform.nfpbackend.smart_transaction.service.SmartTransactionService;
import com.nfplatform.nfpbackend.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/smart-transaction")
public class SmartTransactionController {

    private final SmartTransactionService smartTransactionService;

    @PostMapping("/auction/{auctionId}/buy")
    public void validateBuyTransaction(@ParseUser User user,
                                       @PathVariable(value = "auctionId") Long auctionId,
                                       @RequestBody SmartTransactionDto.KlipPrepareResponse klipPrepareResponse) throws Exception {
        this.smartTransactionService.validateBuyTransaction(user, auctionId, klipPrepareResponse);
    }

    @PostMapping("/piece/register")
    public void validateUploadTransaction(@ParseUser User user,
                                          @RequestPart("registerForm") SmartTransactionDto.UploadPieceValidate uploadPieceValidate) throws Exception {
        this.smartTransactionService.validateUploadTransaction(user, uploadPieceValidate);
    }

    @PostMapping("/piece/sell")
    public void validateSellTransaction(@ParseUser User user,
                                        @RequestBody SmartTransactionDto.SetToSellingValidate setToSellingReq) throws Exception {
        this.smartTransactionService.validateSellTransaction(user, setToSellingReq);
    }
}
