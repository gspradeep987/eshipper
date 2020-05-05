package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.SignatureRequiredDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SignatureRequired} and its DTO {@link SignatureRequiredDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SignatureRequiredMapper extends EntityMapper<SignatureRequiredDTO, SignatureRequired> {



    default SignatureRequired fromId(Long id) {
        if (id == null) {
            return null;
        }
        SignatureRequired signatureRequired = new SignatureRequired();
        signatureRequired.setId(id);
        return signatureRequired;
    }
}
