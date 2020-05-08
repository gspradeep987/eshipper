package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomorderAttachmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomorderAttachment} and its DTO {@link EcomorderAttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {EcomOrderMapper.class})
public interface EcomorderAttachmentMapper extends EntityMapper<EcomorderAttachmentDTO, EcomorderAttachment> {

    @Mapping(source = "ecomOrder.id", target = "ecomOrderId")
    EcomorderAttachmentDTO toDto(EcomorderAttachment ecomorderAttachment);

    @Mapping(source = "ecomOrderId", target = "ecomOrder")
    EcomorderAttachment toEntity(EcomorderAttachmentDTO ecomorderAttachmentDTO);

    default EcomorderAttachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomorderAttachment ecomorderAttachment = new EcomorderAttachment();
        ecomorderAttachment.setId(id);
        return ecomorderAttachment;
    }
}
