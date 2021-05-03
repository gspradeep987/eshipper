package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreSyncDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreSync} and its DTO {@link EcomStoreSyncDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreSyncMapper extends EntityMapper<EcomStoreSyncDTO, EcomStoreSync> {}
