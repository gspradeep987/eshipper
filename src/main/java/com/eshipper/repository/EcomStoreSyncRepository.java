package com.eshipper.repository;

import com.eshipper.domain.EcomStoreSync;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomStoreSync entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStoreSyncRepository extends JpaRepository<EcomStoreSync, Long> {
}
