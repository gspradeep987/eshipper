package com.eshipper.repository;

import com.eshipper.domain.SisyphusClasses;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SisyphusClasses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SisyphusClassesRepository extends JpaRepository<SisyphusClasses, Long> {}
