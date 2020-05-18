package com.eshipper.repository;

import com.eshipper.domain.EcomAutomationRules;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomAutomationRules entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomAutomationRulesRepository extends JpaRepository<EcomAutomationRules, Long> {
}
