package com.greenlog.greenlog.repository;

import com.greenlog.greenlog.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}