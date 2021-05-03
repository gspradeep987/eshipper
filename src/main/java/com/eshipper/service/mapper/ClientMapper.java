package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ClientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = { SisyphusJobMapper.class })
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
  @Mapping(target = "client", source = "client", qualifiedByName = "id")
  ClientDTO toDto(Client s);
}
