package com.eshipper.repository;

import com.eshipper.domain.CommissionReportStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommissionReportStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommissionReportStatusRepository extends JpaRepository<CommissionReportStatus, Long> {
}
