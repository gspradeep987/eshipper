package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupSecondaryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupSecondary} and its DTO {@link EcomMarkupSecondaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomMarkupSecondaryMapper extends EntityMapper<EcomMarkupSecondaryDTO, EcomMarkupSecondary> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomMarkupSecondaryDTO toDtoId(EcomMarkupSecondary ecomMarkupSecondary);
}
