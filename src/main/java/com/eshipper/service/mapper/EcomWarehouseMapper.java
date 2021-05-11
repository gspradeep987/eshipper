package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomWarehouseDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomWarehouse} and its DTO {@link EcomWarehouseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EcomWarehouseMapper extends EntityMapper<EcomWarehouseDTO, EcomWarehouse> {
  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<EcomWarehouseDTO> toDtoIdSet(Set<EcomWarehouse> ecomWarehouse);
}
