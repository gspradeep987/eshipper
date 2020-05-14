package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesCommissionTransferDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesCommissionTransfer} and its DTO {@link WoSalesCommissionTransferDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoSalesCommissionTransferMapper extends EntityMapper<WoSalesCommissionTransferDTO, WoSalesCommissionTransfer> {


    @Mapping(target = "woSalesAgent", ignore = true)
    WoSalesCommissionTransfer toEntity(WoSalesCommissionTransferDTO woSalesCommissionTransferDTO);

    default WoSalesCommissionTransfer fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesCommissionTransfer woSalesCommissionTransfer = new WoSalesCommissionTransfer();
        woSalesCommissionTransfer.setId(id);
        return woSalesCommissionTransfer;
    }
}
