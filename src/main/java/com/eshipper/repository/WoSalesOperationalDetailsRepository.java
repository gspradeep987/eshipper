package com.eshipper.repository;

import com.eshipper.domain.WoSalesOperationalDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoSalesOperationalDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoSalesOperationalDetailsRepository extends JpaRepository<WoSalesOperationalDetails, Long> {
}
