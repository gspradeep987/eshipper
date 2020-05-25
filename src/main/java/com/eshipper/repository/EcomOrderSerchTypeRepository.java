package com.eshipper.repository;

import com.eshipper.domain.EcomOrderSerchType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomOrderSerchType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomOrderSerchTypeRepository extends JpaRepository<EcomOrderSerchType, Long> {
}
