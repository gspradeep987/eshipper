package com.eshipper.repository;

import com.eshipper.domain.CarrierService;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CarrierService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarrierServiceRepository extends JpaRepository<CarrierService, Long> {
}
