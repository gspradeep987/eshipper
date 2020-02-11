package com.eshipper.repository;

import com.eshipper.domain.EcomStore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EcomStore entity.
 */
@Repository
public interface EcomStoreRepository extends JpaRepository<EcomStore, Long> {

    @Query("select ecomStore from EcomStore ecomStore where ecomStore.user.login = ?#{principal.username}")
    List<EcomStore> findByUserIsCurrentUser();

    @Query(value = "select distinct ecomStore from EcomStore ecomStore left join fetch ecomStore.shipmentServices",
        countQuery = "select count(distinct ecomStore) from EcomStore ecomStore")
    Page<EcomStore> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct ecomStore from EcomStore ecomStore left join fetch ecomStore.shipmentServices")
    List<EcomStore> findAllWithEagerRelationships();

    @Query("select ecomStore from EcomStore ecomStore left join fetch ecomStore.shipmentServices where ecomStore.id =:id")
    Optional<EcomStore> findOneWithEagerRelationships(@Param("id") Long id);

}
