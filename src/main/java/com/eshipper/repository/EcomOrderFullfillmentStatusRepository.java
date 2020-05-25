package com.eshipper.repository;

import com.eshipper.domain.EcomOrderFullfillmentStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomOrderFullfillmentStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomOrderFullfillmentStatusRepository extends JpaRepository<EcomOrderFullfillmentStatus, Long> {
}
