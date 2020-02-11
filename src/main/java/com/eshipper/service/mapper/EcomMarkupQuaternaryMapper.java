package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupQuaternary} and its DTO {@link EcomMarkupQuaternaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface EcomMarkupQuaternaryMapper extends EntityMapper<EcomMarkupQuaternaryDTO, EcomMarkupQuaternary> {

    @Mapping(source = "country.id", target = "countryId")
    EcomMarkupQuaternaryDTO toDto(EcomMarkupQuaternary ecomMarkupQuaternary);

    @Mapping(source = "countryId", target = "country")
    @Mapping(target = "ecomStoreMarkup", ignore = true)
    EcomMarkupQuaternary toEntity(EcomMarkupQuaternaryDTO ecomMarkupQuaternaryDTO);

    default EcomMarkupQuaternary fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomMarkupQuaternary ecomMarkupQuaternary = new EcomMarkupQuaternary();
        ecomMarkupQuaternary.setId(id);
        return ecomMarkupQuaternary;
    }
}
