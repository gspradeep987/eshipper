package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesAgentDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesAgentDetails} and its DTO {@link WoSalesAgentDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaymentMethodMapper.class})
public interface WoSalesAgentDetailsMapper extends EntityMapper<WoSalesAgentDetailsDTO, WoSalesAgentDetails> {

    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    WoSalesAgentDetailsDTO toDto(WoSalesAgentDetails woSalesAgentDetails);

    @Mapping(source = "paymentMethodId", target = "paymentMethod")
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
