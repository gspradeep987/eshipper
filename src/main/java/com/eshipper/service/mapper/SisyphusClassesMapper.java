package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.SisyphusClassesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SisyphusClasses} and its DTO {@link SisyphusClassesDTO}.
 */
@Mapper(componentModel = "spring", uses = { SisyphusSubJobMapper.class })
public interface SisyphusClassesMapper extends EntityMapper<SisyphusClassesDTO, SisyphusClasses> {
  @Mapping(target = "classes", source = "classes", qualifiedByName = "id")
  SisyphusClassesDTO toDto(SisyphusClasses s);
}
