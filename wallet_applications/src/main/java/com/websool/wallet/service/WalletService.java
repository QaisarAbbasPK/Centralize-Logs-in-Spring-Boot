package com.websool.wallet.service;

import com.websool.wallet.domain.request.CreateWalletRequest;
import com.websool.wallet.domain.response.ApiResponse;
import java.math.BigDecimal;

public interface WalletService {
    ApiResponse getWalletByUserId(Long userId);
    void updateWalletBalance(Long userId, BigDecimal amount);

    ApiResponse createWallet(CreateWalletRequest request);
}
