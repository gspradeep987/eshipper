package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomMarkupPrimaryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomMarkupPrimary} and its DTO {@link EcomMarkupPrimaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomMarkupPrimaryMapper extends EntityMapper<EcomMarkupPrimaryDTO, EcomMarkupPrimary> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomMarkupPrimaryDTO toDtoId(EcomMarkupPrimary ecomMarkupPrimary);
}
