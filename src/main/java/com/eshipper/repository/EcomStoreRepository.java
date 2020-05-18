package com.eshipper.repository;

import com.eshipper.domain.EcomStore;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStoreRepository extends JpaRepository<EcomStore, Long> {
}
