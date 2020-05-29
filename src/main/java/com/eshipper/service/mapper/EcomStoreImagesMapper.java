package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreImagesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreImages} and its DTO {@link EcomStoreImagesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreImagesMapper extends EntityMapper<EcomStoreImagesDTO, EcomStoreImages> {



    default EcomStoreImages fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStoreImages ecomStoreImages = new EcomStoreImages();
        ecomStoreImages.setId(id);
        return ecomStoreImages;
    }
}
