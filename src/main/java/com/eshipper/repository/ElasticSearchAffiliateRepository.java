package com.eshipper.repository;

import com.eshipper.domain.ElasticSearchAffiliate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ElasticSearchAffiliate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElasticSearchAffiliateRepository extends JpaRepository<ElasticSearchAffiliate, Long> {
}
