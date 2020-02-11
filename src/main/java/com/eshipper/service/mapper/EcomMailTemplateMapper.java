package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMailTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMailTemplate} and its DTO {@link EcomMailTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {EcomStoreMapper.class})
public interface EcomMailTemplateMapper extends EntityMapper<EcomMailTemplateDTO, EcomMailTemplate> {

    @Mapping(source = "ecomStore.id", target = "ecomStoreId")
    EcomMailTemplateDTO toDto(EcomMailTemplate ecomMailTemplate);

    @Mapping(source = "ecomStoreId", target = "ecomStore")
    EcomMailTemplate toEntity(EcomMailTemplateDTO ecomMailTemplateDTO);

    default EcomMailTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomMailTemplate ecomMailTemplate = new EcomMailTemplate();
        ecomMailTemplate.setId(id);
        return ecomMailTemplate;
    }
}
