package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreMarkupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreMarkup} and its DTO {@link EcomStoreMarkupDTO}.
 */
@Mapper(componentModel = "spring", uses = {EcomMarkupPrimaryMapper.class, EcomMarkupSecondaryMapper.class, EcomMarkupTertiaryMapper.class, EcomMarkupQuaternaryMapper.class})
public interface EcomStoreMarkupMapper extends EntityMapper<EcomStoreMarkupDTO, EcomStoreMarkup> {

    @Mapping(source = "ecomMarkupPrimary.id", target = "ecomMarkupPrimaryId")
    @Mapping(source = "ecomMarkupSecondary.id", target = "ecomMarkupSecondaryId")
    @Mapping(source = "ecomMarkupTertiary.id", target = "ecomMarkupTertiaryId")
    @Mapping(source = "ecomMarkupQuaternary.id", target = "ecomMarkupQuaternaryId")
    EcomStoreMarkupDTO toDto(EcomStoreMarkup ecomStoreMarkup);

    @Mapping(source = "ecomMarkupPrimaryId", target = "ecomMarkupPrimary")
    @Mapping(source = "ecomMarkupSecondaryId", target = "ecomMarkupSecondary")
    @Mapping(source = "ecomMarkupTertiaryId", target = "ecomMarkupTertiary")
    @Mapping(source = "ecomMarkupQuaternaryId", target = "ecomMarkupQuaternary")
    @Mapping(target = "ecomStore", ignore = true)
    EcomStoreMarkup toEntity(EcomStoreMarkupDTO ecomStoreMarkupDTO);

    default EcomStoreMarkup fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStoreMarkup ecomStoreMarkup = new EcomStoreMarkup();
        ecomStoreMarkup.setId(id);
        return ecomStoreMarkup;
    }
}
