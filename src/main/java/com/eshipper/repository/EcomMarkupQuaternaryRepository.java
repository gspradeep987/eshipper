package com.eshipper.repository;

import com.eshipper.domain.EcomMarkupQuaternary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomMarkupQuaternary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomMarkupQuaternaryRepository extends JpaRepository<EcomMarkupQuaternary, Long> {}
