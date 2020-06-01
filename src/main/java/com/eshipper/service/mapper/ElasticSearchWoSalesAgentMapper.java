package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.ElasticSearchWoSalesAgentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElasticSearchWoSalesAgent} and its DTO {@link ElasticSearchWoSalesAgentDTO}.
 */
@Mapper(componentModel = "spring", uses = {ElasticStatusMapper.class})
public interface ElasticSearchWoSalesAgentMapper extends EntityMapper<ElasticSearchWoSalesAgentDTO, ElasticSearchWoSalesAgent> {

    @Mapping(source = "status.id", target = "statusId")
    ElasticSearchWoSalesAgentDTO toDto(ElasticSearchWoSalesAgent elasticSearchWoSalesAgent);

    @Mapping(source = "statusId", target = "status")
    @Mapping(target = "woSalesAgent", ignore = true)
    ElasticSearchWoSalesAgent toEntity(ElasticSearchWoSalesAgentDTO elasticSearchWoSalesAgentDTO);

    default ElasticSearchWoSalesAgent fromId(Long id) {
        if (id == null) {
            return null;
        }
        ElasticSearchWoSalesAgent elasticSearchWoSalesAgent = new ElasticSearchWoSalesAgent();
        elasticSearchWoSalesAgent.setId(id);
        return elasticSearchWoSalesAgent;
    }
}
