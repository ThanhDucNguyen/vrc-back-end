package com.vrcserver.vrc.dto;

import com.vrcserver.vrc.dao.models.Booking;
import com.vrcserver.vrc.dao.models.CarOwner;
import com.vrcserver.vrc.dao.models.TypeCar;

import javax.persistence.*;
import java.util.Set;


public class CarDTO {

    private Long id;
    private String model;
    private String type;
    private String yearCar;
    private Integer price;
    private Set<Booking> bookings;
    private TypeCar typeCar;
    private CarOwnerDTO carOwner;

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", yearCar='" + yearCar + '\'' +
                ", price=" + price +
                ", bookings=" + bookings +
                ", typeCar=" + typeCar +
                ", carOwner=" + carOwner +
                '}';
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }


    public CarOwnerDTO getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(CarOwnerDTO carOwner) {
        this.carOwner = carOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYearCar() {
        return yearCar;
    }

    public void setYearCar(String yearCar) {
        this.yearCar = yearCar;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
