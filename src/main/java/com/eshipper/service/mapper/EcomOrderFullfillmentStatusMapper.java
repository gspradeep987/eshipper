package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomOrderFullfillmentStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomOrderFullfillmentStatus} and its DTO {@link EcomOrderFullfillmentStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomOrderFullfillmentStatusMapper extends EntityMapper<EcomOrderFullfillmentStatusDTO, EcomOrderFullfillmentStatus> {



    default EcomOrderFullfillmentStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomOrderFullfillmentStatus ecomOrderFullfillmentStatus = new EcomOrderFullfillmentStatus();
        ecomOrderFullfillmentStatus.setId(id);
        return ecomOrderFullfillmentStatus;
    }
}
