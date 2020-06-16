package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.AffiliateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Affiliate} and its DTO {@link AffiliateDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaymentMethodMapper.class, CompanyMapper.class, FranchiseMapper.class})
public interface AffiliateMapper extends EntityMapper<AffiliateDTO, Affiliate> {

    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    @Mapping(source = "affiliate.id", target = "affiliateId")
    @Mapping(source = "franchise.id", target = "franchiseId")
    AffiliateDTO toDto(Affiliate affiliate);

    @Mapping(target = "affiliatedCustomers", ignore = true)
    @Mapping(target = "removeAffiliatedCustomers", ignore = true)
    @Mapping(source = "paymentMethodId", target = "paymentMethod")
    @Mapping(source = "affiliateId", target = "affiliate")
    @Mapping(source = "franchiseId", target = "franchise")
    Affiliate toEntity(AffiliateDTO affiliateDTO);

    default Affiliate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Affiliate affiliate = new Affiliate();
        affiliate.setId(id);
        return affiliate;
    }
}
