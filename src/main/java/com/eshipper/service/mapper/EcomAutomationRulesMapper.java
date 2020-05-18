package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomAutomationRulesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomAutomationRules} and its DTO {@link EcomAutomationRulesDTO}.
 */
@Mapper(componentModel = "spring", uses = {EcomStoreMapper.class})
public interface EcomAutomationRulesMapper extends EntityMapper<EcomAutomationRulesDTO, EcomAutomationRules> {

    @Mapping(source = "ecomStore.id", target = "ecomStoreId")
    EcomAutomationRulesDTO toDto(EcomAutomationRules ecomAutomationRules);

    @Mapping(source = "ecomStoreId", target = "ecomStore")
    EcomAutomationRules toEntity(EcomAutomationRulesDTO ecomAutomationRulesDTO);

    default EcomAutomationRules fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomAutomationRules ecomAutomationRules = new EcomAutomationRules();
        ecomAutomationRules.setId(id);
        return ecomAutomationRules;
    }
}
