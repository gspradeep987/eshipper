package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CompanyEcomSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CompanyEcomSettings} and its DTO {@link CompanyEcomSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SignatureRequiredMapper.class, CarrierSettingsMapper.class})
public interface CompanyEcomSettingsMapper extends EntityMapper<CompanyEcomSettingsDTO, CompanyEcomSettings> {

    @Mapping(source = "signatureRequired.id", target = "signatureRequiredId")
    CompanyEcomSettingsDTO toDto(CompanyEcomSettings companyEcomSettings);

    @Mapping(source = "signatureRequiredId", target = "signatureRequired")
    @Mapping(target = "removeCarrierSettings", ignore = true)
    CompanyEcomSettings toEntity(CompanyEcomSettingsDTO companyEcomSettingsDTO);

    default CompanyEcomSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyEcomSettings companyEcomSettings = new CompanyEcomSettings();
        companyEcomSettings.setId(id);
        return companyEcomSettings;
    }
}
