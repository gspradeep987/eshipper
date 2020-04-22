package com.eshipper.repository;

import com.eshipper.domain.CompanyEcomSettings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the CompanyEcomSettings entity.
 */
@Repository
public interface CompanyEcomSettingsRepository extends JpaRepository<CompanyEcomSettings, Long> {

    @Query(value = "select distinct companyEcomSettings from CompanyEcomSettings companyEcomSettings left join fetch companyEcomSettings.carrierSettings",
        countQuery = "select count(distinct companyEcomSettings) from CompanyEcomSettings companyEcomSettings")
    Page<CompanyEcomSettings> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct companyEcomSettings from CompanyEcomSettings companyEcomSettings left join fetch companyEcomSettings.carrierSettings")
    List<CompanyEcomSettings> findAllWithEagerRelationships();

    @Query("select companyEcomSettings from CompanyEcomSettings companyEcomSettings left join fetch companyEcomSettings.carrierSettings where companyEcomSettings.id =:id")
    Optional<CompanyEcomSettings> findOneWithEagerRelationships(@Param("id") Long id);
}
