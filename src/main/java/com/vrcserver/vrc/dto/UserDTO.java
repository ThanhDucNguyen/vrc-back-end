package com.vrcserver.vrc.dto;

import com.vrcserver.vrc.dao.models.Booking;
import com.vrcserver.vrc.dao.models.CarOwner;

import javax.persistence.*;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String userName;
    private String userPhone;
    private String userEmail;
    private Integer role;
    private String password;
    private String message;
    private Set<BookingDTO> bookings;
    private Set<CarOwnerDTO> carOwners;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", message='" + message + '\'' +
                ", bookings=" + bookings +
                ", carOwners=" + carOwners +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CarOwnerDTO> getCarOwners() {
        return carOwners;
    }

    public void setCarOwners(Set<CarOwnerDTO> carOwners) {
        this.carOwners = carOwners;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
