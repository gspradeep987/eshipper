package com.eshipper.repository;

import com.eshipper.domain.SisyphusSubJob;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SisyphusSubJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SisyphusSubJobRepository extends JpaRepository<SisyphusSubJob, Long> {}
