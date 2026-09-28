package com.greenlog.greenlog.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trees")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Species is required")
    private String species;

    @NotNull(message = "Planting date is required")
    private LocalDate plantedDate;

    @NotBlank(message = "Latitude is required")
    private String latitude;

    @NotBlank(message = "Longitude is required")
    private String longitude;

    private String status = "ALIVE";

    @ManyToOne
    @JoinColumn(name = "plantation_drive_id", nullable = false)
    private PlantationDrive plantationDrive;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL)
    private List<CheckIn> checkIns = new ArrayList<>();

    public Tree() {
    }

    public Tree(String species, LocalDate plantedDate,
                String latitude, String longitude,
                PlantationDrive plantationDrive,
                Volunteer volunteer) {

        this.species = species;
        this.plantedDate = plantedDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.plantationDrive = plantationDrive;
        this.volunteer = volunteer;
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public LocalDate getPlantedDate() {
        return plantedDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getStatus() {
        return status;
    }

    public PlantationDrive getPlantationDrive() {
        return plantationDrive;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    @JsonIgnore
    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setPlantedDate(LocalDate plantedDate) {
        this.plantedDate = plantedDate;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlantationDrive(PlantationDrive plantationDrive) {
        this.plantationDrive = plantationDrive;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }
}