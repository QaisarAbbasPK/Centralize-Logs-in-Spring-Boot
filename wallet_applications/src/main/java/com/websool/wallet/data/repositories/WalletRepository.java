package com.websool.wallet.data.repositories;

import com.websool.wallet.data.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    WalletEntity findByUserId(Long userId);
}
