package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomStoreColorThemeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomStoreColorTheme} and its DTO {@link EcomStoreColorThemeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomStoreColorThemeMapper extends EntityMapper<EcomStoreColorThemeDTO, EcomStoreColorTheme> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  EcomStoreColorThemeDTO toDtoId(EcomStoreColorTheme ecomStoreColorTheme);
}
