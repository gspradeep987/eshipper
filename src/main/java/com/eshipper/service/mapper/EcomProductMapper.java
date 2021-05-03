package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomProductDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomProduct} and its DTO {@link EcomProductDTO}.
 */
@Mapper(componentModel = "spring", uses = { CountryMapper.class, EcomWarehouseMapper.class, EcomOrderMapper.class })
public interface EcomProductMapper extends EntityMapper<EcomProductDTO, EcomProduct> {
  @Mapping(target = "country", source = "country", qualifiedByName = "id")
  @Mapping(target = "ecomWarehouses", source = "ecomWarehouses", qualifiedByName = "idSet")
  @Mapping(target = "ecomOrder", source = "ecomOrder", qualifiedByName = "id")
  EcomProductDTO toDto(EcomProduct s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomProductDTO toDtoId(EcomProduct ecomProduct);

  @Mapping(target = "removeEcomWarehouse", ignore = true)
  EcomProduct toEntity(EcomProductDTO ecomProductDTO);
}
