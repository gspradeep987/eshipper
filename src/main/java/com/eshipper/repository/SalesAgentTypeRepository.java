package com.eshipper.repository;

import com.eshipper.domain.SalesAgentType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SalesAgentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesAgentTypeRepository extends JpaRepository<SalesAgentType, Long> {
}
