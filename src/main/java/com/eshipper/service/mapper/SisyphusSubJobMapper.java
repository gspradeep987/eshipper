package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.SisyphusSubJobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SisyphusSubJob} and its DTO {@link SisyphusSubJobDTO}.
 */
@Mapper(componentModel = "spring", uses = { SisyphusJobMapper.class })
public interface SisyphusSubJobMapper extends EntityMapper<SisyphusSubJobDTO, SisyphusSubJob> {
  @Mapping(target = "sisyphusJob", source = "sisyphusJob", qualifiedByName = "id")
  SisyphusSubJobDTO toDto(SisyphusSubJob s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  SisyphusSubJobDTO toDtoId(SisyphusSubJob sisyphusSubJob);
}
