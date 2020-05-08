package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomOrder} and its DTO {@link EcomOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomOrderMapper extends EntityMapper<EcomOrderDTO, EcomOrder> {



    default EcomOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomOrder ecomOrder = new EcomOrder();
        ecomOrder.setId(id);
        return ecomOrder;
    }
}
