package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.User10DTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link User10} and its DTO {@link User10DTO}.
 */
@Mapper(componentModel = "spring", uses = {WoSalesAgentMapper.class})
public interface User10Mapper extends EntityMapper<User10DTO, User10> {

    @Mapping(source = "woSalesAgent.id", target = "woSalesAgentId")
    User10DTO toDto(User10 user10);

    @Mapping(source = "woSalesAgentId", target = "woSalesAgent")
    User10 toEntity(User10DTO user10DTO);

    default User10 fromId(Long id) {
        if (id == null) {
            return null;
        }
        User10 user10 = new User10();
        user10.setId(id);
        return user10;
    }
}
