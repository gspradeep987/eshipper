package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CompanyCarrierAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CompanyCarrierAccount} and its DTO {@link CompanyCarrierAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, CarrierMapper.class, FranchiseMapper.class})
public interface CompanyCarrierAccountMapper extends EntityMapper<CompanyCarrierAccountDTO, CompanyCarrierAccount> {

    @Mapping(source = "region.id", target = "regionId")
    @Mapping(source = "carrier.id", target = "carrierId")
    @Mapping(source = "franchise.id", target = "franchiseId")
    CompanyCarrierAccountDTO toDto(CompanyCarrierAccount companyCarrierAccount);

    @Mapping(source = "regionId", target = "region")
    @Mapping(source = "carrierId", target = "carrier")
    @Mapping(source = "franchiseId", target = "franchise")
    CompanyCarrierAccount toEntity(CompanyCarrierAccountDTO companyCarrierAccountDTO);

    default CompanyCarrierAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyCarrierAccount companyCarrierAccount = new CompanyCarrierAccount();
        companyCarrierAccount.setId(id);
        return companyCarrierAccount;
    }
}
