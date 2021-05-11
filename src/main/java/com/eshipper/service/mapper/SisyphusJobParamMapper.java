package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.SisyphusJobParamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SisyphusJobParam} and its DTO {@link SisyphusJobParamDTO}.
 */
@Mapper(componentModel = "spring", uses = { SisyphusJobMapper.class })
public interface SisyphusJobParamMapper extends EntityMapper<SisyphusJobParamDTO, SisyphusJobParam> {
  @Mapping(target = "sisyphusJob", source = "sisyphusJob", qualifiedByName = "id")
  SisyphusJobParamDTO toDto(SisyphusJobParam s);
}
