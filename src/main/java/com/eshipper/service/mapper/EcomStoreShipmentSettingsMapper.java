package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreShipmentSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreShipmentSettings} and its DTO {@link EcomStoreShipmentSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreShipmentSettingsMapper extends EntityMapper<EcomStoreShipmentSettingsDTO, EcomStoreShipmentSettings> {


    @Mapping(target = "ecomStore", ignore = true)
    EcomStoreShipmentSettings toEntity(EcomStoreShipmentSettingsDTO ecomStoreShipmentSettingsDTO);

    default EcomStoreShipmentSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStoreShipmentSettings ecomStoreShipmentSettings = new EcomStoreShipmentSettings();
        ecomStoreShipmentSettings.setId(id);
        return ecomStoreShipmentSettings;
    }
}
