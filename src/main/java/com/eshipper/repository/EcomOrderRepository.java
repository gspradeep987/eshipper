package com.eshipper.repository;

import com.eshipper.domain.EcomOrder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomOrderRepository extends JpaRepository<EcomOrder, Long> {}
