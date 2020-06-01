package com.eshipper.repository;

import com.eshipper.domain.ElasticSearchWoSalesAgent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ElasticSearchWoSalesAgent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElasticSearchWoSalesAgentRepository extends JpaRepository<ElasticSearchWoSalesAgent, Long> {
}
