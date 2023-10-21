package com.websool.wallet.service.impl;

import com.websool.wallet.data.entities.UsersEntity;
import com.websool.wallet.data.entities.WalletEntity;
import com.websool.wallet.data.repositories.WalletRepository;
import com.websool.wallet.domain.request.CreateWalletRequest;
import com.websool.wallet.domain.response.ApiResponse;
import com.websool.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public ApiResponse getWalletByUserId(Long userId) {
        return new ApiResponse(walletRepository.findByUserId(userId));
    }

    @Override
    public ApiResponse createWallet(CreateWalletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersEntity data = (UsersEntity) authentication.getPrincipal();
        WalletEntity wallet = new WalletEntity();
        wallet.setUserId(data.getId());
        wallet.setBalance(request.getInitialBalance());
        return new ApiResponse(walletRepository.save(wallet));
    }

    @Override
    public void updateWalletBalance(Long userId, BigDecimal amount) {
        WalletEntity wallet = walletRepository.findByUserId(userId);
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
    }
}

