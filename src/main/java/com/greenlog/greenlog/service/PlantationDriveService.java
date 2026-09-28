package com.greenlog.greenlog.service;

import com.greenlog.greenlog.entity.PlantationDrive;
import com.greenlog.greenlog.repository.PlantationDriveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantationDriveService {

    private final PlantationDriveRepository plantationDriveRepository;

    public PlantationDriveService(PlantationDriveRepository plantationDriveRepository) {
        this.plantationDriveRepository = plantationDriveRepository;
    }

    public List<PlantationDrive> getAllPlantationDrives() {
        return plantationDriveRepository.findAll();
    }

    public Optional<PlantationDrive> getPlantationDriveById(Long id) {
        return plantationDriveRepository.findById(id);
    }

    public PlantationDrive savePlantationDrive(PlantationDrive plantationDrive) {
        return plantationDriveRepository.save(plantationDrive);
    }

    public void deletePlantationDrive(Long id) {
        plantationDriveRepository.deleteById(id);
    }
}