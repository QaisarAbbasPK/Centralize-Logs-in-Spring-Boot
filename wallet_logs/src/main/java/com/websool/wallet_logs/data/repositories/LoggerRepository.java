package com.websool.wallet_logs.data.repositories;

import com.websool.wallet_logs.data.entities.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
}
