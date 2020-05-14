package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesAgentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesAgent} and its DTO {@link WoSalesAgentDTO}.
 */
@Mapper(componentModel = "spring", uses = {WoSalesAgentDetailsMapper.class, WoSalesCommissionDetailsMapper.class, WoSalesOperationalDetailsMapper.class, WoSalesCommissionTransferMapper.class, SalesAgentTypeMapper.class})
public interface WoSalesAgentMapper extends EntityMapper<WoSalesAgentDTO, WoSalesAgent> {

    @Mapping(source = "woSalesAgentDetails.id", target = "woSalesAgentDetailsId")
    @Mapping(source = "woSalesCommissionDetails.id", target = "woSalesCommissionDetailsId")
    @Mapping(source = "woSalesOperationalDetails.id", target = "woSalesOperationalDetailsId")
    @Mapping(source = "woSalesCommissionTransfer.id", target = "woSalesCommissionTransferId")
    @Mapping(source = "salesAgentType.id", target = "salesAgentTypeId")
    WoSalesAgentDTO toDto(WoSalesAgent woSalesAgent);

    @Mapping(source = "woSalesAgentDetailsId", target = "woSalesAgentDetails")
    @Mapping(source = "woSalesCommissionDetailsId", target = "woSalesCommissionDetails")
    @Mapping(source = "woSalesOperationalDetailsId", target = "woSalesOperationalDetails")
    @Mapping(source = "woSalesCommissionTransferId", target = "woSalesCommissionTransfer")
    @Mapping(source = "salesAgentTypeId", target = "salesAgentType")
    WoSalesAgent toEntity(WoSalesAgentDTO woSalesAgentDTO);

    default WoSalesAgent fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesAgent woSalesAgent = new WoSalesAgent();
        woSalesAgent.setId(id);
        return woSalesAgent;
    }
}
