package com.eshipper.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.eshipper.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JobTypeDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(JobTypeDTO.class);
    JobTypeDTO jobTypeDTO1 = new JobTypeDTO();
    jobTypeDTO1.setId(1L);
    JobTypeDTO jobTypeDTO2 = new JobTypeDTO();
    assertThat(jobTypeDTO1).isNotEqualTo(jobTypeDTO2);
    jobTypeDTO2.setId(jobTypeDTO1.getId());
    assertThat(jobTypeDTO1).isEqualTo(jobTypeDTO2);
    jobTypeDTO2.setId(2L);
    assertThat(jobTypeDTO1).isNotEqualTo(jobTypeDTO2);
    jobTypeDTO1.setId(null);
    assertThat(jobTypeDTO1).isNotEqualTo(jobTypeDTO2);
  }
}
