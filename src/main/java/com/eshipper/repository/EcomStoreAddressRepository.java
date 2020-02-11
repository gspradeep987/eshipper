package com.eshipper.repository;

import com.eshipper.domain.EcomStoreAddress;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomStoreAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomStoreAddressRepository extends JpaRepository<EcomStoreAddress, Long> {

}
