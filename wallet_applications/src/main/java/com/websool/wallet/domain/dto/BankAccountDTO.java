package com.websool.wallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private Integer accountNo;
    private String ifscCode;
    private String bankName;
    private double balance;
}
