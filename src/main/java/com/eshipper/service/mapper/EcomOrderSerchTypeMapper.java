package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomOrderSerchTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomOrderSerchType} and its DTO {@link EcomOrderSerchTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomOrderSerchTypeMapper extends EntityMapper<EcomOrderSerchTypeDTO, EcomOrderSerchType> {



    default EcomOrderSerchType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomOrderSerchType ecomOrderSerchType = new EcomOrderSerchType();
        ecomOrderSerchType.setId(id);
        return ecomOrderSerchType;
    }
}
