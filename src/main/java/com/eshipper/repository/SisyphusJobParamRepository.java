package com.eshipper.repository;

import com.eshipper.domain.SisyphusJobParam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SisyphusJobParam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SisyphusJobParamRepository extends JpaRepository<SisyphusJobParam, Long> {}
