package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomWarehouseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomWarehouse} and its DTO {@link EcomWarehouseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomWarehouseMapper extends EntityMapper<EcomWarehouseDTO, EcomWarehouse> {


    @Mapping(target = "ecomProducts", ignore = true)
    @Mapping(target = "removeEcomProduct", ignore = true)
    EcomWarehouse toEntity(EcomWarehouseDTO ecomWarehouseDTO);

    default EcomWarehouse fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomWarehouse ecomWarehouse = new EcomWarehouse();
        ecomWarehouse.setId(id);
        return ecomWarehouse;
    }
}
