package com.eshipper.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JobTypeMapperTest {

  private JobTypeMapper jobTypeMapper;

  @BeforeEach
  public void setUp() {
    jobTypeMapper = new JobTypeMapperImpl();
  }
}
