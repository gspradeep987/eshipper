package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomProductImageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomProductImage} and its DTO {@link EcomProductImageDTO}.
 */
@Mapper(componentModel = "spring", uses = { EcomProductMapper.class })
public interface EcomProductImageMapper extends EntityMapper<EcomProductImageDTO, EcomProductImage> {
  @Mapping(target = "ecomProduct", source = "ecomProduct", qualifiedByName = "id")
  EcomProductImageDTO toDto(EcomProductImage s);
}
