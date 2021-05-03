package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomStoreShipmentSettingsMapperTest {

  private EcomStoreShipmentSettingsMapper ecomStoreShipmentSettingsMapper;

  @BeforeEach
  public void setUp() {
    ecomStoreShipmentSettingsMapper = new EcomStoreShipmentSettingsMapperImpl();
  }
}
