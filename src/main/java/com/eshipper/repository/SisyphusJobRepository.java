package com.eshipper.repository;

import com.eshipper.domain.SisyphusJob;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SisyphusJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SisyphusJobRepository extends JpaRepository<SisyphusJob, Long> {}
