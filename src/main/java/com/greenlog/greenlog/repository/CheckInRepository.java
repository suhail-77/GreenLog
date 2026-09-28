package com.greenlog.greenlog.repository;

import com.greenlog.greenlog.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
}