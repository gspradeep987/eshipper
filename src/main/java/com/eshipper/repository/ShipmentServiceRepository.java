package com.eshipper.repository;

import com.eshipper.domain.ShipmentService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShipmentService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShipmentServiceRepository extends JpaRepository<ShipmentService, Long> {}
