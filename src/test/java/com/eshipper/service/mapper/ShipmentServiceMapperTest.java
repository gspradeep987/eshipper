package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipmentServiceMapperTest {

  private ShipmentServiceMapper shipmentServiceMapper;

  @BeforeEach
  public void setUp() {
    shipmentServiceMapper = new ShipmentServiceMapperImpl();
  }
}
