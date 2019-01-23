package com.vrcserver.vrc.dto;

import com.vrcserver.vrc.dao.models.Car;

import java.util.Set;

public class TypeCarDTO {
    private Long id;
    private String name;
    private String image;
    private String message;
    private Set<CarDTO> cars;

    @Override
    public String toString() {
        return "TypeCarDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", message='" + message + '\'' +
                ", cars=" + cars +
                '}';
    }

    public Set<CarDTO> getCars() {
        return cars;
    }

    public void setCars(Set<CarDTO> cars) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
