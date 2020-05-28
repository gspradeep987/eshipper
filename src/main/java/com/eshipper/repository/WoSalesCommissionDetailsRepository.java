package com.eshipper.repository;

import com.eshipper.domain.WoSalesCommissionDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoSalesCommissionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoSalesCommissionDetailsRepository extends JpaRepository<WoSalesCommissionDetails, Long> {
}
