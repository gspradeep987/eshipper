package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesOperationalDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesOperationalDetails} and its DTO {@link WoSalesOperationalDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoSalesOperationalDetailsMapper extends EntityMapper<WoSalesOperationalDetailsDTO, WoSalesOperationalDetails> {


    @Mapping(target = "woSalesOperationalCarriers", ignore = true)
    @Mapping(target = "removeWoSalesOperationalCarrier", ignore = true)
    @Mapping(target = "woSalesAgent", ignore = true)
    WoSalesOperationalDetails toEntity(WoSalesOperationalDetailsDTO woSalesOperationalDetailsDTO);

    default WoSalesOperationalDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesOperationalDetails woSalesOperationalDetails = new WoSalesOperationalDetails();
        woSalesOperationalDetails.setId(id);
        return woSalesOperationalDetails;
    }
}
