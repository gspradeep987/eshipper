package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EcomOrderAttachmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EcomOrderAttachment} and its DTO {@link EcomOrderAttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {EcomOrderMapper.class})
public interface EcomOrderAttachmentMapper extends EntityMapper<EcomOrderAttachmentDTO, EcomOrderAttachment> {

    @Mapping(source = "ecomOrder.id", target = "ecomOrderId")
    EcomOrderAttachmentDTO toDto(EcomOrderAttachment ecomOrderAttachment);

    @Mapping(source = "ecomOrderId", target = "ecomOrder")
    EcomOrderAttachment toEntity(EcomOrderAttachmentDTO ecomOrderAttachmentDTO);

    default EcomOrderAttachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        EcomOrderAttachment ecomOrderAttachment = new EcomOrderAttachment();
        ecomOrderAttachment.setId(id);
        return ecomOrderAttachment;
    }
}
