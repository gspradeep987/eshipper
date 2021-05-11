package com.eshipper.repository;

import com.eshipper.domain.EcomProduct;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EcomProduct entity.
 */
@Repository
public interface EcomProductRepository extends JpaRepository<EcomProduct, Long> {
  @Query(
    value = "select distinct ecomProduct from EcomProduct ecomProduct left join fetch ecomProduct.ecomWarehouses",
    countQuery = "select count(distinct ecomProduct) from EcomProduct ecomProduct"
  )
  Page<EcomProduct> findAllWithEagerRelationships(Pageable pageable);

  @Query("select distinct ecomProduct from EcomProduct ecomProduct left join fetch ecomProduct.ecomWarehouses")
  List<EcomProduct> findAllWithEagerRelationships();

  @Query("select ecomProduct from EcomProduct ecomProduct left join fetch ecomProduct.ecomWarehouses where ecomProduct.id =:id")
  Optional<EcomProduct> findOneWithEagerRelationships(@Param("id") Long id);
}
