package com.eshipper.repository;

import com.eshipper.domain.EcomWarehouse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomWarehouse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomWarehouseRepository extends JpaRepository<EcomWarehouse, Long> {}
