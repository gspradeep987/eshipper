package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreMarkupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreMarkup} and its DTO {@link EcomStoreMarkupDTO}.
 */
@Mapper(
  componentModel = "spring",
  uses = {
    EcomMarkupPrimaryMapper.class, EcomMarkupSecondaryMapper.class, EcomMarkupTertiaryMapper.class, EcomMarkupQuaternaryMapper.class,
  }
)
public interface EcomStoreMarkupMapper extends EntityMapper<EcomStoreMarkupDTO, EcomStoreMarkup> {
  @Mapping(target = "ecomMarkupPrimary", source = "ecomMarkupPrimary", qualifiedByName = "id")
  @Mapping(target = "ecomMarkupSecondary", source = "ecomMarkupSecondary", qualifiedByName = "id")
  @Mapping(target = "ecomMarkupTertiary", source = "ecomMarkupTertiary", qualifiedByName = "id")
  @Mapping(target = "ecomMarkupQuaternary", source = "ecomMarkupQuaternary", qualifiedByName = "id")
  EcomStoreMarkupDTO toDto(EcomStoreMarkup s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomStoreMarkupDTO toDtoId(EcomStoreMarkup ecomStoreMarkup);
}
