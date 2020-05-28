package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesOperationalCarrierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesOperationalCarrier} and its DTO {@link WoSalesOperationalCarrierDTO}.
 */
@Mapper(componentModel = "spring", uses = {CarrierMapper.class, CarrierServiceMapper.class, WoSalesOperationalDetailsMapper.class})
public interface WoSalesOperationalCarrierMapper extends EntityMapper<WoSalesOperationalCarrierDTO, WoSalesOperationalCarrier> {

    @Mapping(source = "carrier.id", target = "carrierId")
    @Mapping(source = "carrierService.id", target = "carrierServiceId")
    @Mapping(source = "woSalesOperationalDetails.id", target = "woSalesOperationalDetailsId")
    WoSalesOperationalCarrierDTO toDto(WoSalesOperationalCarrier woSalesOperationalCarrier);

    @Mapping(source = "carrierId", target = "carrier")
    @Mapping(source = "carrierServiceId", target = "carrierService")
    @Mapping(source = "woSalesOperationalDetailsId", target = "woSalesOperationalDetails")
    WoSalesOperationalCarrier toEntity(WoSalesOperationalCarrierDTO woSalesOperationalCarrierDTO);

    default WoSalesOperationalCarrier fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesOperationalCarrier woSalesOperationalCarrier = new WoSalesOperationalCarrier();
        woSalesOperationalCarrier.setId(id);
        return woSalesOperationalCarrier;
    }
}
