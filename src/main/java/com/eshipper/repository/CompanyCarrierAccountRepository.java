package com.eshipper.repository;

import com.eshipper.domain.CompanyCarrierAccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CompanyCarrierAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyCarrierAccountRepository extends JpaRepository<CompanyCarrierAccount, Long> {
}
