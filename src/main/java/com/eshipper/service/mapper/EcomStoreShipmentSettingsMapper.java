package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreShipmentSettings} and its DTO {@link EcomStoreShipmentSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreShipmentSettingsMapper extends EntityMapper<EcomStoreShipmentSettingsDTO, EcomStoreShipmentSettings> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomStoreShipmentSettingsDTO toDtoId(EcomStoreShipmentSettings ecomStoreShipmentSettings);
}
