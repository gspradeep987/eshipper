package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStore} and its DTO {@link EcomStoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreMapper extends EntityMapper<EcomStoreDTO, EcomStore> {



    default EcomStore fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStore ecomStore = new EcomStore();
        ecomStore.setId(id);
        return ecomStore;
    }
}
