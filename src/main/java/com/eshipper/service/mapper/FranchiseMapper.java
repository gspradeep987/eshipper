package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.FranchiseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Franchise} and its DTO {@link FranchiseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FranchiseMapper extends EntityMapper<FranchiseDTO, Franchise> {



    default Franchise fromId(Long id) {
        if (id == null) {
            return null;
        }
        Franchise franchise = new Franchise();
        franchise.setId(id);
        return franchise;
    }
}
