package com.eshipper.repository;

import com.eshipper.domain.EcomOrderAttachment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EcomOrderAttachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EcomOrderAttachmentRepository extends JpaRepository<EcomOrderAttachment, Long> {
}
