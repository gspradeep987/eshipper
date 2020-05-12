package com.eshipper.repository;

import com.eshipper.domain.WoSalesOperationalCarrier;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoSalesOperationalCarrier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoSalesOperationalCarrierRepository extends JpaRepository<WoSalesOperationalCarrier, Long> {
}
