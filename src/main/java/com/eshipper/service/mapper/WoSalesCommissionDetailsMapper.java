package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesCommissionDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesCommissionDetails} and its DTO {@link WoSalesCommissionDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoSalesCommissionDetailsMapper extends EntityMapper<WoSalesCommissionDetailsDTO, WoSalesCommissionDetails> {


    @Mapping(target = "woSalesCommissionCarriers", ignore = true)
    @Mapping(target = "removeWoSalesCommissionCarrier", ignore = true)
    @Mapping(target = "woSalesAgent", ignore = true)
    WoSalesCommissionDetails toEntity(WoSalesCommissionDetailsDTO woSalesCommissionDetailsDTO);

    default WoSalesCommissionDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesCommissionDetails woSalesCommissionDetails = new WoSalesCommissionDetails();
        woSalesCommissionDetails.setId(id);
        return woSalesCommissionDetails;
    }
}
