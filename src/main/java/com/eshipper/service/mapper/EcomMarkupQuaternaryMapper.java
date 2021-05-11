package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupQuaternaryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupQuaternary} and its DTO {@link EcomMarkupQuaternaryDTO}.
 */
@Mapper(componentModel = "spring", uses = { CountryMapper.class })
public interface EcomMarkupQuaternaryMapper extends EntityMapper<EcomMarkupQuaternaryDTO, EcomMarkupQuaternary> {
  @Mapping(target = "country", source = "country", qualifiedByName = "id")
  EcomMarkupQuaternaryDTO toDto(EcomMarkupQuaternary s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomMarkupQuaternaryDTO toDtoId(EcomMarkupQuaternary ecomMarkupQuaternary);
}
