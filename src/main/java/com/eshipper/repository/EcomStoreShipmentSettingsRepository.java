package com.eshipper.repository;

import com.eshipper.domain.EcomStoreShipmentSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomStoreShipmentSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStoreShipmentSettingsRepository extends JpaRepository<EcomStoreShipmentSettings, Long> {}
