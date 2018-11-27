package com.vrcserver.vrc.dto;

import com.vrcserver.vrc.dao.models.Car;

import java.util.Set;

public class TypeCarDTO {
    private Long id;
    private String name;
    private Set<Car> cars;

    @Override
    public String toString() {
        return "TypeCarDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
