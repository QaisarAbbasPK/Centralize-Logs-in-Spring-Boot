package com.websool.wallet.controllers;

import com.websool.wallet.data.entities.UsersEntity;
import com.websool.wallet.domain.request.CreateWalletRequest;
import com.websool.wallet.domain.response.ApiResponse;
import com.websool.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}")
    public ApiResponse getWalletByUserId(@PathVariable Long userId) {
        return walletService.getWalletByUserId(userId);
    }

    @PostMapping("create-wallet")
    public ApiResponse createWallet(@RequestBody CreateWalletRequest request) {
        return walletService.createWallet(request);
    }

    @PutMapping("/{userId}/{amount}")
    public void updateWalletBalance(@PathVariable Long userId, @PathVariable BigDecimal amount) {
        walletService.updateWalletBalance(userId, amount);
    }
}

