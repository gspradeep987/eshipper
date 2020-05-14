package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CarrierServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarrierService} and its DTO {@link CarrierServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarrierServiceMapper extends EntityMapper<CarrierServiceDTO, CarrierService> {



    default CarrierService fromId(Long id) {
        if (id == null) {
            return null;
        }
        CarrierService carrierService = new CarrierService();
        carrierService.setId(id);
        return carrierService;
    }
}
