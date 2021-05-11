package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMailTemplateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMailTemplate} and its DTO {@link EcomMailTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = { EcomStoreMapper.class })
public interface EcomMailTemplateMapper extends EntityMapper<EcomMailTemplateDTO, EcomMailTemplate> {
  @Mapping(target = "ecomStore", source = "ecomStore", qualifiedByName = "id")
  EcomMailTemplateDTO toDto(EcomMailTemplate s);
}
