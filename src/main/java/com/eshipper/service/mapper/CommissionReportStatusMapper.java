package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CommissionReportStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommissionReportStatus} and its DTO {@link CommissionReportStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommissionReportStatusMapper extends EntityMapper<CommissionReportStatusDTO, CommissionReportStatus> {



    default CommissionReportStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommissionReportStatus commissionReportStatus = new CommissionReportStatus();
        commissionReportStatus.setId(id);
        return commissionReportStatus;
    }
}
