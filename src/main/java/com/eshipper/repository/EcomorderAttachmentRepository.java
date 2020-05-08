package com.eshipper.repository;

import com.eshipper.domain.EcomorderAttachment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomorderAttachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomorderAttachmentRepository extends JpaRepository<EcomorderAttachment, Long> {
}
