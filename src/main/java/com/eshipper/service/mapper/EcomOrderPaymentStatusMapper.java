package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomOrderPaymentStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomOrderPaymentStatus} and its DTO {@link EcomOrderPaymentStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomOrderPaymentStatusMapper extends EntityMapper<EcomOrderPaymentStatusDTO, EcomOrderPaymentStatus> {



    default EcomOrderPaymentStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomOrderPaymentStatus ecomOrderPaymentStatus = new EcomOrderPaymentStatus();
        ecomOrderPaymentStatus.setId(id);
        return ecomOrderPaymentStatus;
    }
}
