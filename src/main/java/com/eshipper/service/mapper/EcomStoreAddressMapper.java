package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreAddressDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreAddress} and its DTO {@link EcomStoreAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = { CountryMapper.class, ProvinceMapper.class, CityMapper.class })
public interface EcomStoreAddressMapper extends EntityMapper<EcomStoreAddressDTO, EcomStoreAddress> {
  @Mapping(target = "country", source = "country", qualifiedByName = "id")
  @Mapping(target = "province", source = "province", qualifiedByName = "id")
  @Mapping(target = "city", source = "city", qualifiedByName = "id")
  EcomStoreAddressDTO toDto(EcomStoreAddress s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomStoreAddressDTO toDtoId(EcomStoreAddress ecomStoreAddress);
}
