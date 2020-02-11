package com.eshipper.repository;

import com.eshipper.domain.EcomMarkupPrimary;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomMarkupPrimary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomMarkupPrimaryRepository extends JpaRepository<EcomMarkupPrimary, Long> {

}
