package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupTertiaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupTertiary} and its DTO {@link EcomMarkupTertiaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomMarkupTertiaryMapper extends EntityMapper<EcomMarkupTertiaryDTO, EcomMarkupTertiary> {


    @Mapping(target = "ecomStoreMarkup", ignore = true)
    EcomMarkupTertiary toEntity(EcomMarkupTertiaryDTO ecomMarkupTertiaryDTO);

    default EcomMarkupTertiary fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomMarkupTertiary ecomMarkupTertiary = new EcomMarkupTertiary();
        ecomMarkupTertiary.setId(id);
        return ecomMarkupTertiary;
    }
}
