package com.eshipper.repository;

import com.eshipper.domain.EcomMarkupTertiary;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomMarkupTertiary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomMarkupTertiaryRepository extends JpaRepository<EcomMarkupTertiary, Long> {

}
