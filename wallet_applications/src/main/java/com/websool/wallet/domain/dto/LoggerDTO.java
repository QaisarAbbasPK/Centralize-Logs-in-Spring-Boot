package com.websool.wallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoggerDTO {
    private String endPoint;
    private String headers;
    private String method;
    private String request;
    private String response;
}
