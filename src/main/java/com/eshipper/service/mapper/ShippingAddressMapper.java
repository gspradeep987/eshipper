package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ShippingAddressDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShippingAddress} and its DTO {@link ShippingAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShippingAddressMapper extends EntityMapper<ShippingAddressDTO, ShippingAddress> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  ShippingAddressDTO toDtoId(ShippingAddress shippingAddress);
}
