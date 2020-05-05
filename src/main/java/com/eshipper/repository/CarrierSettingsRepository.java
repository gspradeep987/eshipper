package com.eshipper.repository;

import com.eshipper.domain.CarrierSettings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CarrierSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarrierSettingsRepository extends JpaRepository<CarrierSettings, Long> {
}
