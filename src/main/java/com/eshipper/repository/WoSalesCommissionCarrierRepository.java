package com.eshipper.repository;

import com.eshipper.domain.WoSalesCommissionCarrier;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoSalesCommissionCarrier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoSalesCommissionCarrierRepository extends JpaRepository<WoSalesCommissionCarrier, Long> {
}
