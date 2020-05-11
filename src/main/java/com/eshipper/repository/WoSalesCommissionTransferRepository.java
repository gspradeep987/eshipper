package com.eshipper.repository;

import com.eshipper.domain.WoSalesCommissionTransfer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoSalesCommissionTransfer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoSalesCommissionTransferRepository extends JpaRepository<WoSalesCommissionTransfer, Long> {
}
