package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomProductImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomProductImage} and its DTO {@link EcomProductImageDTO}.
 */
@Mapper(componentModel = "spring", uses = {EcomProductMapper.class})
public interface EcomProductImageMapper extends EntityMapper<EcomProductImageDTO, EcomProductImage> {

    @Mapping(source = "ecomProduct.id", target = "ecomProductId")
    EcomProductImageDTO toDto(EcomProductImage ecomProductImage);

    @Mapping(source = "ecomProductId", target = "ecomProduct")
    EcomProductImage toEntity(EcomProductImageDTO ecomProductImageDTO);

    default EcomProductImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomProductImage ecomProductImage = new EcomProductImage();
        ecomProductImage.setId(id);
        return ecomProductImage;
    }
}
