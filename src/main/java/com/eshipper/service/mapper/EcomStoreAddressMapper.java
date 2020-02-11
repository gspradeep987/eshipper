package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreAddress} and its DTO {@link EcomStoreAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, ProvinceMapper.class, CityMapper.class})
public interface EcomStoreAddressMapper extends EntityMapper<EcomStoreAddressDTO, EcomStoreAddress> {

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "city.id", target = "cityId")
    EcomStoreAddressDTO toDto(EcomStoreAddress ecomStoreAddress);

    @Mapping(source = "countryId", target = "country")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "cityId", target = "city")
    @Mapping(target = "ecomStore", ignore = true)
    EcomStoreAddress toEntity(EcomStoreAddressDTO ecomStoreAddressDTO);

    default EcomStoreAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomStoreAddress ecomStoreAddress = new EcomStoreAddress();
        ecomStoreAddress.setId(id);
        return ecomStoreAddress;
    }
}
