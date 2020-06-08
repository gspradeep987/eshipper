package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.ElasticSearchAffiliateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElasticSearchAffiliate} and its DTO {@link ElasticSearchAffiliateDTO}.
 */
@Mapper(componentModel = "spring", uses = {AffiliateMapper.class, ElasticStatusMapper.class})
public interface ElasticSearchAffiliateMapper extends EntityMapper<ElasticSearchAffiliateDTO, ElasticSearchAffiliate> {

    @Mapping(source = "affiliate.id", target = "affiliateId")
    @Mapping(source = "elasticStatus.id", target = "elasticStatusId")
    ElasticSearchAffiliateDTO toDto(ElasticSearchAffiliate elasticSearchAffiliate);

    @Mapping(source = "affiliateId", target = "affiliate")
    @Mapping(source = "elasticStatusId", target = "elasticStatus")
    ElasticSearchAffiliate toEntity(ElasticSearchAffiliateDTO elasticSearchAffiliateDTO);

    default ElasticSearchAffiliate fromId(Long id) {
        if (id == null) {
            return null;
        }
        ElasticSearchAffiliate elasticSearchAffiliate = new ElasticSearchAffiliate();
        elasticSearchAffiliate.setId(id);
        return elasticSearchAffiliate;
    }
}
