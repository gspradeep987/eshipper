package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.SisyphusClientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SisyphusClient} and its DTO {@link SisyphusClientDTO}.
 */
@Mapper(componentModel = "spring", uses = { SisyphusJobMapper.class })
public interface SisyphusClientMapper extends EntityMapper<SisyphusClientDTO, SisyphusClient> {
  @Mapping(target = "sisyphusClient", source = "sisyphusClient", qualifiedByName = "id")
  SisyphusClientDTO toDto(SisyphusClient s);
}
