package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomOrder} and its DTO {@link EcomOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class, ShippingAddressMapper.class, EcomStoreMapper.class})
public interface EcomOrderMapper extends EntityMapper<EcomOrderDTO, EcomOrder> {

    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "shippingAddress.id", target = "shippingAddressId")
    @Mapping(source = "billingAddress.id", target = "billingAddressId")
    @Mapping(source = "ecomStore.id", target = "ecomStoreId")
    EcomOrderDTO toDto(EcomOrder ecomOrder);

    @Mapping(target = "ecomProducts", ignore = true)
    @Mapping(target = "removeEcomProduct", ignore = true)
    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "shippingAddressId", target = "shippingAddress")
    @Mapping(source = "billingAddressId", target = "billingAddress")
    @Mapping(source = "ecomStoreId", target = "ecomStore")
    EcomOrder toEntity(EcomOrderDTO ecomOrderDTO);

    default EcomOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomOrder ecomOrder = new EcomOrder();
        ecomOrder.setId(id);
        return ecomOrder;
    }
}
