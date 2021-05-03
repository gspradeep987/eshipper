package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ProvinceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Province} and its DTO {@link ProvinceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProvinceMapper extends EntityMapper<ProvinceDTO, Province> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  ProvinceDTO toDtoId(Province province);
}
