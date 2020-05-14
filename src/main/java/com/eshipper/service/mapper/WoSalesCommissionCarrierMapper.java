package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesCommissionCarrierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesCommissionCarrier} and its DTO {@link WoSalesCommissionCarrierDTO}.
 */
@Mapper(componentModel = "spring", uses = {CarrierMapper.class, CarrierServiceMapper.class, WoSalesCommissionDetailsMapper.class})
public interface WoSalesCommissionCarrierMapper extends EntityMapper<WoSalesCommissionCarrierDTO, WoSalesCommissionCarrier> {

    @Mapping(source = "carrier.id", target = "carrierId")
    @Mapping(source = "carrierService.id", target = "carrierServiceId")
    @Mapping(source = "woSalesCommissionDetails.id", target = "woSalesCommissionDetailsId")
    WoSalesCommissionCarrierDTO toDto(WoSalesCommissionCarrier woSalesCommissionCarrier);

    @Mapping(source = "carrierId", target = "carrier")
    @Mapping(source = "carrierServiceId", target = "carrierService")
    @Mapping(source = "woSalesCommissionDetailsId", target = "woSalesCommissionDetails")
    WoSalesCommissionCarrier toEntity(WoSalesCommissionCarrierDTO woSalesCommissionCarrierDTO);

    default WoSalesCommissionCarrier fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesCommissionCarrier woSalesCommissionCarrier = new WoSalesCommissionCarrier();
        woSalesCommissionCarrier.setId(id);
        return woSalesCommissionCarrier;
    }
}
