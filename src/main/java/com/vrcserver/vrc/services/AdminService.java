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

    TypeCarDTO removeTypeCar(Long id);

    CarDTO removeCar(Long id);

    CarOwnerDTO removeCarOwner(Long id);

    BookingDTO removeBooking(Long id);

    UserDTO removeUser(Long id);

    TypeCarDTO addTypeCar(TypeCarDTO typeCarDTO);

    CarDTO addCar(CarDTO carDTO);

    CarOwnerDTO addCarOwner(CarOwnerDTO carOwnerDTO);

    UserDTO addUser(UserDTO userDTO);
}
