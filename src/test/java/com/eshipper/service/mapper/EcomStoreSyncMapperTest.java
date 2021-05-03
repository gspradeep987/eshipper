package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcomStoreSyncMapperTest {

  private EcomStoreSyncMapper ecomStoreSyncMapper;

  @BeforeEach
  public void setUp() {
    ecomStoreSyncMapper = new EcomStoreSyncMapperImpl();
  }
}
