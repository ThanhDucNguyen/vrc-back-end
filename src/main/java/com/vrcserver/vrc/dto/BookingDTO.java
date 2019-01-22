package com.vrcserver.vrc.dto;

import com.vrcserver.vrc.dao.models.Car;
import com.vrcserver.vrc.dao.models.User;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class BookingDTO {
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date bookingDate = new Date();
    private Boolean pay;
    private Double price;
    private String rentalDay;
    private String returnDay;
    private CarDTO car;
    private UserDTO user;

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRentalDay() {
        return rentalDay;
    }

    public void setRentalDay(String rentalDay) {
        this.rentalDay = rentalDay;
    }

    public String getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(String returnDay) {
        this.returnDay = returnDay;
    }
}
