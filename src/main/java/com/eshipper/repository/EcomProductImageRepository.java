package com.eshipper.repository;

import com.eshipper.domain.EcomProductImage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomProductImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomProductImageRepository extends JpaRepository<EcomProductImage, Long> {

}
