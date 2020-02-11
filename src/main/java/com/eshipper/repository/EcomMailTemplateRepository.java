package com.eshipper.repository;

import com.eshipper.domain.EcomMailTemplate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomMailTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomMailTemplateRepository extends JpaRepository<EcomMailTemplate, Long> {

}
