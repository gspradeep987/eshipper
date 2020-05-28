package com.eshipper.repository;

import com.eshipper.domain.WoSalesAgent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoSalesAgent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoSalesAgentRepository extends JpaRepository<WoSalesAgent, Long> {
}
