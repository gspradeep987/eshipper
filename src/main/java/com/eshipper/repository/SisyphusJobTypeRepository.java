package com.eshipper.repository;

import com.eshipper.domain.SisyphusJobType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SisyphusJobType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SisyphusJobTypeRepository extends JpaRepository<SisyphusJobType, Long> {}
