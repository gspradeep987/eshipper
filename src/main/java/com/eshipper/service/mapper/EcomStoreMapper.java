package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStore} and its DTO {@link EcomStoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {EcomStoreAddressMapper.class, EcomStoreColorThemeMapper.class, EcomStoreShipmentSettingsMapper.class, EcomStorePackageSettingsMapper.class, EcomStoreMarkupMapper.class, CompanyMapper.class, UserMapper.class, EcomProductMapper.class, ShipmentServiceMapper.class})
public interface EcomStoreMapper extends EntityMapper<EcomStoreDTO, EcomStore> {

    @Mapping(source = "ecomStoreAddress.id", target = "ecomStoreAddressId")
    @Mapping(source = "ecomStoreColorTheme.id", target = "ecomStoreColorThemeId")
    @Mapping(source = "ecomStoreShipmentSettings.id", target = "ecomStoreShipmentSettingsId")
    @Mapping(source = "ecomStorePackageSettings.id", target = "ecomStorePackageSettingsId")
    @Mapping(source = "ecomStoreMarkup.id", target = "ecomStoreMarkupId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "ecomProduct.id", target = "ecomProductId")
    EcomStoreDTO toDto(EcomStore ecomStore);

    @Mapping(source = "ecomStoreAddressId", target = "ecomStoreAddress")
    @Mapping(source = "ecomStoreColorThemeId", target = "ecomStoreColorTheme")
    @Mapping(source = "ecomStoreShipmentSettingsId", target = "ecomStoreShipmentSettings")
    @Mapping(source = "ecomStorePackageSettingsId", target = "ecomStorePackageSettings")
    @Mapping(source = "ecomStoreMarkupId", target = "ecomStoreMarkup")
    @Mapping(target = "ecomMailTemplates", ignore = true)
    @Mapping(target = "removeEcomMailTemplate", ignore = true)
    @Mapping(target = "ecomOrders", ignore = true)
    @Mapping(target = "removeEcomOrder", ignore = true)
    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "ecomProductId", target = "ecomProduct")
    @Mapping(target = "removeShipmentService", ignore = true)
    EcomStore toEntity(EcomStoreDTO ecomStoreDTO);

    default EcomStore fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStore ecomStore = new EcomStore();
        ecomStore.setId(id);
        return ecomStore;
    }
}
