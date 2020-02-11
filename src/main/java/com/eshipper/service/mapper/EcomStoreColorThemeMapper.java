package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreColorThemeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreColorTheme} and its DTO {@link EcomStoreColorThemeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreColorThemeMapper extends EntityMapper<EcomStoreColorThemeDTO, EcomStoreColorTheme> {


    @Mapping(target = "ecomStore", ignore = true)
    EcomStoreColorTheme toEntity(EcomStoreColorThemeDTO ecomStoreColorThemeDTO);

    default EcomStoreColorTheme fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStoreColorTheme ecomStoreColorTheme = new EcomStoreColorTheme();
        ecomStoreColorTheme.setId(id);
        return ecomStoreColorTheme;
    }
}
