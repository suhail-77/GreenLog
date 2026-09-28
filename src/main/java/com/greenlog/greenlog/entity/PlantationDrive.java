package com.greenlog.greenlog.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plantation_drives")
public class PlantationDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Drive name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Drive date is required")
    private LocalDate driveDate;

    private String description;

    @OneToMany(mappedBy = "plantationDrive", cascade = CascadeType.ALL)
    private List<Tree> trees = new ArrayList<>();

    public PlantationDrive() {
    }

    public PlantationDrive(String name, String location,
                           LocalDate driveDate, String description) {
        this.name = name;
        this.location = location;
        this.driveDate = driveDate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDriveDate() {
        return driveDate;
    }

    public String getDescription() {
        return description;
    }

    @JsonIgnore
    public List<Tree> getTrees() {
        return trees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }
}