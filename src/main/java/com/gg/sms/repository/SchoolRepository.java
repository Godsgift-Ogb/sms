package com.gg.sms.repository;

import com.gg.sms.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    Boolean existsByEmail (String email);
    School findByEmail(String email);
}
