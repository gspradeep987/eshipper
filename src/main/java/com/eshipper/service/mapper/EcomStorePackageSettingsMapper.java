package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStorePackageSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStorePackageSettings} and its DTO {@link EcomStorePackageSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStorePackageSettingsMapper extends EntityMapper<EcomStorePackageSettingsDTO, EcomStorePackageSettings> {


    @Mapping(target = "ecomStore", ignore = true)
    EcomStorePackageSettings toEntity(EcomStorePackageSettingsDTO ecomStorePackageSettingsDTO);

    default EcomStorePackageSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStorePackageSettings ecomStorePackageSettings = new EcomStorePackageSettings();
        ecomStorePackageSettings.setId(id);
        return ecomStorePackageSettings;
    }
}
