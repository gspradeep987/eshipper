package com.eshipper.repository;

import com.eshipper.domain.WoSalesAgentDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoSalesAgentDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoSalesAgentDetailsRepository extends JpaRepository<WoSalesAgentDetails, Long> {
}
