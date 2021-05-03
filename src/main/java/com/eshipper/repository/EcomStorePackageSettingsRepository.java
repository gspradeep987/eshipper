package com.eshipper.repository;

import com.eshipper.domain.EcomStorePackageSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomStorePackageSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStorePackageSettingsRepository extends JpaRepository<EcomStorePackageSettings, Long> {}
