package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.AffiliateCommissionReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AffiliateCommissionReport} and its DTO {@link AffiliateCommissionReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {AffiliateMapper.class, CurrencyMapper.class})
public interface AffiliateCommissionReportMapper extends EntityMapper<AffiliateCommissionReportDTO, AffiliateCommissionReport> {

    @Mapping(source = "affiliate.id", target = "affiliateId")
    @Mapping(source = "currency.id", target = "currencyId")
    AffiliateCommissionReportDTO toDto(AffiliateCommissionReport affiliateCommissionReport);

    @Mapping(target = "shipments", ignore = true)
    @Mapping(target = "removeShipments", ignore = true)
    @Mapping(source = "affiliateId", target = "affiliate")
    @Mapping(source = "currencyId", target = "currency")
    AffiliateCommissionReport toEntity(AffiliateCommissionReportDTO affiliateCommissionReportDTO);

    default AffiliateCommissionReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        AffiliateCommissionReport affiliateCommissionReport = new AffiliateCommissionReport();
        affiliateCommissionReport.setId(id);
        return affiliateCommissionReport;
    }
}
