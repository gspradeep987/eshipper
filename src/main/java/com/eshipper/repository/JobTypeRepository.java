package com.eshipper.repository;

import com.eshipper.domain.JobType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JobType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Long> {}
