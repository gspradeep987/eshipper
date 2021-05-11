package com.eshipper.repository;

import com.eshipper.domain.SisyphusClient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SisyphusClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SisyphusClientRepository extends JpaRepository<SisyphusClient, Long> {}
