package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.SalesAgentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesAgentType} and its DTO {@link SalesAgentTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SalesAgentTypeMapper extends EntityMapper<SalesAgentTypeDTO, SalesAgentType> {



    default SalesAgentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesAgentType salesAgentType = new SalesAgentType();
        salesAgentType.setId(id);
        return salesAgentType;
    }
}
