package com.eshipper.repository;

import com.eshipper.domain.EcomOrderPaymentStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomOrderPaymentStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomOrderPaymentStatusRepository extends JpaRepository<EcomOrderPaymentStatus, Long> {
}
