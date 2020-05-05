package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CarrierSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarrierSettings} and its DTO {@link CarrierSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarrierSettingsMapper extends EntityMapper<CarrierSettingsDTO, CarrierSettings> {


    @Mapping(target = "companyEcomSettings", ignore = true)
    @Mapping(target = "removeCompanyEcomSettings", ignore = true)
    CarrierSettings toEntity(CarrierSettingsDTO carrierSettingsDTO);

    default CarrierSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        CarrierSettings carrierSettings = new CarrierSettings();
        carrierSettings.setId(id);
        return carrierSettings;
    }
}
