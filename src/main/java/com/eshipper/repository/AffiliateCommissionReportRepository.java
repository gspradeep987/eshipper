package com.eshipper.repository;

import com.eshipper.domain.AffiliateCommissionReport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AffiliateCommissionReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AffiliateCommissionReportRepository extends JpaRepository<AffiliateCommissionReport, Long> {
}
