package com.eshipper.repository;

import com.eshipper.domain.User10;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the User10 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface User10Repository extends JpaRepository<User10, Long> {
}
