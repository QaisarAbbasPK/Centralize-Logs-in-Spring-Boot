package com.websool.wallet_logs.consumers;

import com.websool.wallet_logs.data.entities.LoggerEntity;
import com.websool.wallet_logs.data.repositories.LoggerRepository;
import com.websool.wallet_logs.dto.LoggerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoggerConsumer {

    private final LoggerRepository loggerRepository;

    @RabbitListener(queues = "finance_logs_queue")
    public void receiveMessage(LoggerDTO loggerDTO) {
        LoggerEntity loggerEntity = LoggerEntity.builder()
                .endPoint(loggerDTO.getEndPoint())
                .headers(loggerDTO.getHeaders())
                .method(loggerDTO.getMethod())
                .request(loggerDTO.getRequest())
                .response(loggerDTO.getResponse())
                .build();
        loggerRepository.save(loggerEntity);
    }
}

