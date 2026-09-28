package com.greenlog.greenlog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "check_ins")
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Check-in date is required")
    private LocalDate checkInDate;

    @NotNull(message = "Survival status is required")
    private Boolean alive;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "tree_id", nullable = false)
    private Tree tree;

    @ManyToOne
    @JoinColumn(name = "volunteer_id", nullable = false)
    private Volunteer volunteer;

    public CheckIn() {
    }

    public CheckIn(LocalDate checkInDate, Boolean alive,
                   String remarks, Tree tree) {
        this.checkInDate = checkInDate;
        this.alive = alive;
        this.remarks = remarks;
        this.tree = tree;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public Boolean getAlive() {
        return alive;
    }

    public String getRemarks() {
        return remarks;
    }

    public Tree getTree() {
        return tree;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}