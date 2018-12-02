package com.vrcserver.vrc.dto;

import com.vrcserver.vrc.dao.models.Car;
import com.vrcserver.vrc.dao.models.User;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class BookingDTO {
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    private Boolean pay;
    private Integer price;
    private Date rentalDay;
    private Date returnDay;
    private CarDTO car;
    private UserDTO user;

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", date=" + date +
                ", pay=" + pay +
                ", price=" + price +
                ", rentalDay=" + rentalDay +
                ", returnDay=" + returnDay +
                ", car=" + car +
                ", user=" + user +
                '}';
    }

    public CarDTO getCar() {
        return car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getRentalDay() {
        return rentalDay;
    }

    public void setRentalDay(Date rentalDay) {
        this.rentalDay = rentalDay;
    }

    public Date getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(Date returnDay) {
        this.returnDay = returnDay;
    }
}
