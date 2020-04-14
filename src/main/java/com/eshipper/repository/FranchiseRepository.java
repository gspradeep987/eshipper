package com.eshipper.repository;

import com.eshipper.domain.Franchise;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Franchise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
