package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.SisyphusJobTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SisyphusJobType} and its DTO {@link SisyphusJobTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = { SisyphusJobMapper.class })
public interface SisyphusJobTypeMapper extends EntityMapper<SisyphusJobTypeDTO, SisyphusJobType> {
  @Mapping(target = "sisyphusJobType", source = "sisyphusJobType", qualifiedByName = "id")
  SisyphusJobTypeDTO toDto(SisyphusJobType s);
}
