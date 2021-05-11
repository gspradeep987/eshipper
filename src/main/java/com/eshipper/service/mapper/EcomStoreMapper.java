package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStore} and its DTO {@link EcomStoreDTO}.
 */
@Mapper(
  componentModel = "spring",
  uses = {
    EcomStoreAddressMapper.class,
    EcomStoreColorThemeMapper.class,
    EcomStoreShipmentSettingsMapper.class,
    EcomStorePackageSettingsMapper.class,
    EcomStoreMarkupMapper.class,
    CompanyMapper.class,
    UserMapper.class,
    EcomProductMapper.class,
    ShipmentServiceMapper.class,
  }
)
public interface EcomStoreMapper extends EntityMapper<EcomStoreDTO, EcomStore> {
  @Mapping(target = "ecomStoreAddress", source = "ecomStoreAddress", qualifiedByName = "id")
  @Mapping(target = "ecomStoreColorTheme", source = "ecomStoreColorTheme", qualifiedByName = "id")
  @Mapping(target = "ecomStoreShipmentSettings", source = "ecomStoreShipmentSettings", qualifiedByName = "id")
  @Mapping(target = "ecomStorePackageSettings", source = "ecomStorePackageSettings", qualifiedByName = "id")
  @Mapping(target = "ecomStoreMarkup", source = "ecomStoreMarkup", qualifiedByName = "id")
  @Mapping(target = "company", source = "company", qualifiedByName = "id")
  @Mapping(target = "user", source = "user", qualifiedByName = "id")
  @Mapping(target = "ecomProduct", source = "ecomProduct", qualifiedByName = "id")
  @Mapping(target = "shipmentServices", source = "shipmentServices", qualifiedByName = "idSet")
  EcomStoreDTO toDto(EcomStore s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomStoreDTO toDtoId(EcomStore ecomStore);

  @Mapping(target = "removeShipmentService", ignore = true)
  EcomStore toEntity(EcomStoreDTO ecomStoreDTO);
}
