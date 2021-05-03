package com.eshipper.repository;

import com.eshipper.domain.EcomStoreMarkup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomStoreMarkup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStoreMarkupRepository extends JpaRepository<EcomStoreMarkup, Long> {}
