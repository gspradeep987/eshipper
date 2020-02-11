package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupSecondaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupSecondary} and its DTO {@link EcomMarkupSecondaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomMarkupSecondaryMapper extends EntityMapper<EcomMarkupSecondaryDTO, EcomMarkupSecondary> {


    @Mapping(target = "ecomStoreMarkup", ignore = true)
    EcomMarkupSecondary toEntity(EcomMarkupSecondaryDTO ecomMarkupSecondaryDTO);

    default EcomMarkupSecondary fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomMarkupSecondary ecomMarkupSecondary = new EcomMarkupSecondary();
        ecomMarkupSecondary.setId(id);
        return ecomMarkupSecondary;
    }
}
