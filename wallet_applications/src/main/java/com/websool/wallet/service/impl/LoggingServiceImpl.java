package com.websool.wallet.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websool.wallet.domain.dto.LoggerDTO;
import com.websool.wallet.service.LoggingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Log
@Service
public class LoggingServiceImpl implements LoggingService {

    private LoggerDTO loggerDTO = new LoggerDTO();
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public LoggingServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        loggerDTO.setMethod(httpServletRequest.getMethod());
        loggerDTO.setRequest(convertObjectToJson(body));
        loggerDTO.setEndPoint(httpServletRequest.getRequestURI());
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        loggerDTO.setHeaders(buildHeadersMap(httpServletRequest).toString());
        loggerDTO.setResponse(convertObjectToJson(body));
        rabbitTemplate.convertAndSend("finance_logs_queue", loggerDTO);
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private String convertObjectToJson(Object yourObject) {
        try {
            return objectMapper.writeValueAsString(yourObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
