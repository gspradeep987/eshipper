package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesAgentDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesAgentDetails} and its DTO {@link WoSalesAgentDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoSalesAgentDetailsMapper extends EntityMapper<WoSalesAgentDetailsDTO, WoSalesAgentDetails> {


    @Mapping(target = "woSalesAgent", ignore = true)
    WoSalesAgentDetails toEntity(WoSalesAgentDetailsDTO woSalesAgentDetailsDTO);

    default WoSalesAgentDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesAgentDetails woSalesAgentDetails = new WoSalesAgentDetails();
        woSalesAgentDetails.setId(id);
        return woSalesAgentDetails;
    }
}
