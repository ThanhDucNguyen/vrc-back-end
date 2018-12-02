package com.vrcserver.vrc.services;

import com.vrcserver.vrc.dto.*;

import java.util.List;

public interface AdminService {
    UserDTO login(UserDTO userDTO);

    List<TypeCarDTO> getTypeCar();

    List<CarDTO> getCar();

    List<CarOwnerDTO> getCarOwner();

    List<BookingDTO> getBooking();

    List<UserDTO> getUser();
}
