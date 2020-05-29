package com.eshipper.repository;

import com.eshipper.domain.EcomStoreImages;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomStoreImages entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStoreImagesRepository extends JpaRepository<EcomStoreImages, Long> {
}
