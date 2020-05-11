package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.PaymentMethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentMethod} and its DTO {@link PaymentMethodDTO}.
 */
@Mapper(componentModel = "spring", uses = {WoSalesAgentDetailsMapper.class})
public interface PaymentMethodMapper extends EntityMapper<PaymentMethodDTO, PaymentMethod> {

    @Mapping(source = "woSalesAgentDetails.id", target = "woSalesAgentDetailsId")
    PaymentMethodDTO toDto(PaymentMethod paymentMethod);

    @Mapping(source = "woSalesAgentDetailsId", target = "woSalesAgentDetails")
    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);

    default PaymentMethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(id);
        return paymentMethod;
    }
}
