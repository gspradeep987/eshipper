package com.eshipper.repository;

import com.eshipper.domain.SignatureRequired;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SignatureRequired entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SignatureRequiredRepository extends JpaRepository<SignatureRequired, Long> {
}
