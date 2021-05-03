package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomOrderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomOrder} and its DTO {@link EcomOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = { CurrencyMapper.class, ShippingAddressMapper.class, EcomStoreMapper.class })
public interface EcomOrderMapper extends EntityMapper<EcomOrderDTO, EcomOrder> {
  @Mapping(target = "currency", source = "currency", qualifiedByName = "id")
  @Mapping(target = "shippingAddress", source = "shippingAddress", qualifiedByName = "id")
  @Mapping(target = "billingAddress", source = "billingAddress", qualifiedByName = "id")
  @Mapping(target = "ecomStore", source = "ecomStore", qualifiedByName = "id")
  EcomOrderDTO toDto(EcomOrder s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomOrderDTO toDtoId(EcomOrder ecomOrder);
}
