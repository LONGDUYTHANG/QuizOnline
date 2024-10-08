/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author ADMIN
 */
public class RegistrationList {
    String email;
    LocalDateTime registration_time;
    String subject_name;
    String package_name;
    double cost;
    String status;

    public RegistrationList() {
    }

    public RegistrationList(String email, LocalDateTime registration_time, String subject_name, String package_name, double cost, String status) {
        this.email = email;
        this.registration_time = registration_time;
        this.subject_name = subject_name;
        this.package_name = package_name;
        this.cost = cost;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(LocalDateTime registration_time) {
        this.registration_time = registration_time;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
