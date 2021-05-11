package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupTertiaryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupTertiary} and its DTO {@link EcomMarkupTertiaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomMarkupTertiaryMapper extends EntityMapper<EcomMarkupTertiaryDTO, EcomMarkupTertiary> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomMarkupTertiaryDTO toDtoId(EcomMarkupTertiary ecomMarkupTertiary);
}
