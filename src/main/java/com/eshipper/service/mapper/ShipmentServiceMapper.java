package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ShipmentServiceDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShipmentService} and its DTO {@link ShipmentServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShipmentServiceMapper extends EntityMapper<ShipmentServiceDTO, ShipmentService> {
  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<ShipmentServiceDTO> toDtoIdSet(Set<ShipmentService> shipmentService);
}
