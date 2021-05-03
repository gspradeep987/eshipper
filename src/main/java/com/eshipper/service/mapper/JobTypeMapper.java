package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.JobTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link JobType} and its DTO {@link JobTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = { SisyphusJobMapper.class })
public interface JobTypeMapper extends EntityMapper<JobTypeDTO, JobType> {
  @Mapping(target = "jobType", source = "jobType", qualifiedByName = "id")
  JobTypeDTO toDto(JobType s);
}
