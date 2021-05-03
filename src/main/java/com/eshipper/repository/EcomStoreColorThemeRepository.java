package com.eshipper.repository;

import com.eshipper.domain.EcomStoreColorTheme;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomStoreColorTheme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStoreColorThemeRepository extends JpaRepository<EcomStoreColorTheme, Long> {}
