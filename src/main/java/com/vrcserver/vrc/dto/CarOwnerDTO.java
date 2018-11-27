package com.vrcserver.vrc.dto;

import com.vrcserver.vrc.dao.models.Car;

import javax.persistence.*;
import java.util.Set;

public class CarOwnerDTO {

    private Long id;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;
    private String ownerAddress;
    private Set<Car> cars;

    @Override
    public String toString() {
        return "CarOwnerDTO{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", cars=" + cars +
                '}';
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }
}
