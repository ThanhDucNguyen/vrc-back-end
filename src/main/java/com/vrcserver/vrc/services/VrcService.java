package com.vrcserver.vrc.services;

import com.vrcserver.vrc.dao.models.TypeCar;
import com.vrcserver.vrc.dao.models.User;
import com.vrcserver.vrc.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VrcService {
    UserDTO login(UserDTO userDTO);

    List<TypeCarDTO> listTypeCar();

    List<CarDTO> listCar(Long id);

    List<CarDTO> searchCar(SearchDTO searchDTO);

    User checkUserName(UserDTO userDTO);

    void register(UserDTO userDTO);

    void rentCar(BookingDTO bookingDTO);
}
