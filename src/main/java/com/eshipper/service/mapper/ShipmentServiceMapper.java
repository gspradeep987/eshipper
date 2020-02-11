package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.ShipmentServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShipmentService} and its DTO {@link ShipmentServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShipmentServiceMapper extends EntityMapper<ShipmentServiceDTO, ShipmentService> {


    @Mapping(target = "ecomStores", ignore = true)
    @Mapping(target = "removeEcomStore", ignore = true)
    ShipmentService toEntity(ShipmentServiceDTO shipmentServiceDTO);

    default ShipmentService fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShipmentService shipmentService = new ShipmentService();
        shipmentService.setId(id);
        return shipmentService;
    }
}
