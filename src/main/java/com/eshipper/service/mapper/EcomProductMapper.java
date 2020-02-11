package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomProduct} and its DTO {@link EcomProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, EcomWarehouseMapper.class, EcomOrderMapper.class})
public interface EcomProductMapper extends EntityMapper<EcomProductDTO, EcomProduct> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "ecomOrder.id", target = "ecomOrderId")
    EcomProductDTO toDto(EcomProduct ecomProduct);

    @Mapping(target = "ecomProductImages", ignore = true)
    @Mapping(target = "removeEcomProductImage", ignore = true)
    @Mapping(source = "countryId", target = "country")
    @Mapping(target = "removeEcomWarehouse", ignore = true)
    @Mapping(source = "ecomOrderId", target = "ecomOrder")
    EcomProduct toEntity(EcomProductDTO ecomProductDTO);

    default EcomProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomProduct ecomProduct = new EcomProduct();
        ecomProduct.setId(id);
        return ecomProduct;
    }
}
