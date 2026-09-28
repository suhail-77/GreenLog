package com.greenlog.greenlog.service;

import com.greenlog.greenlog.entity.CheckIn;
import com.greenlog.greenlog.repository.CheckInRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;

    public CheckInService(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }

    public Optional<CheckIn> getCheckInById(Long id) {
        return checkInRepository.findById(id);
    }

    public CheckIn saveCheckIn(CheckIn checkIn) {
        return checkInRepository.save(checkIn);
    }

    public void deleteCheckIn(Long id) {
        checkInRepository.deleteById(id);
    }
}