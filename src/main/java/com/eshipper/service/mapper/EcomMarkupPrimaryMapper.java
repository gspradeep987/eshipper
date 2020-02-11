package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupPrimaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupPrimary} and its DTO {@link EcomMarkupPrimaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomMarkupPrimaryMapper extends EntityMapper<EcomMarkupPrimaryDTO, EcomMarkupPrimary> {


    @Mapping(target = "ecomStoreMarkup", ignore = true)
    EcomMarkupPrimary toEntity(EcomMarkupPrimaryDTO ecomMarkupPrimaryDTO);

    default EcomMarkupPrimary fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomMarkupPrimary ecomMarkupPrimary = new EcomMarkupPrimary();
        ecomMarkupPrimary.setId(id);
        return ecomMarkupPrimary;
    }
}
