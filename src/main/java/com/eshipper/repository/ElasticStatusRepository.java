package com.eshipper.repository;

import com.eshipper.domain.ElasticStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ElasticStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElasticStatusRepository extends JpaRepository<ElasticStatus, Long> {
}
