package com.eshipper.repository;

import com.eshipper.domain.EcomMarkupSecondary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomMarkupSecondary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomMarkupSecondaryRepository extends JpaRepository<EcomMarkupSecondary, Long> {}
