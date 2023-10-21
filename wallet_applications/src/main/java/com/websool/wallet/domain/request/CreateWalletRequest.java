package com.websool.wallet.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateWalletRequest {
    private BigDecimal initialBalance;
}
