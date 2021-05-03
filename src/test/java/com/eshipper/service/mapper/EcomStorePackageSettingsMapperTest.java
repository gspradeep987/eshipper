package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomStorePackageSettingsMapperTest {

  private EcomStorePackageSettingsMapper ecomStorePackageSettingsMapper;

  @BeforeEach
  public void setUp() {
    ecomStorePackageSettingsMapper = new EcomStorePackageSettingsMapperImpl();
  }
}
