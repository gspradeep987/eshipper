package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.SisyphusJobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SisyphusJob} and its DTO {@link SisyphusJobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SisyphusJobMapper extends EntityMapper<SisyphusJobDTO, SisyphusJob> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  SisyphusJobDTO toDtoId(SisyphusJob sisyphusJob);
}
