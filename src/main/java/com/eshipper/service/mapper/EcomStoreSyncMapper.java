package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreSyncDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreSync} and its DTO {@link EcomStoreSyncDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreSyncMapper extends EntityMapper<EcomStoreSyncDTO, EcomStoreSync> {



    default EcomStoreSync fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStoreSync ecomStoreSync = new EcomStoreSync();
        ecomStoreSync.setId(id);
        return ecomStoreSync;
    }
}
