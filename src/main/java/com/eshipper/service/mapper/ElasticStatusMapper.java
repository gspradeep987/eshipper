package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.ElasticStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElasticStatus} and its DTO {@link ElasticStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ElasticStatusMapper extends EntityMapper<ElasticStatusDTO, ElasticStatus> {



    default ElasticStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ElasticStatus elasticStatus = new ElasticStatus();
        elasticStatus.setId(id);
        return elasticStatus;
    }
}
