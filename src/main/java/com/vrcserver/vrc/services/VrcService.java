package com.vrcserver.vrc.services;

import com.vrcserver.vrc.dao.models.TypeCar;
import com.vrcserver.vrc.dao.models.User;
import com.vrcserver.vrc.dto.BookingDTO;
import com.vrcserver.vrc.dto.CarDTO;
import com.vrcserver.vrc.dto.SearchDTO;
import com.vrcserver.vrc.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VrcService {
    UserDTO login(UserDTO userDTO);

    List<TypeCar> listTypeCar();

    List<CarDTO> listCar(Long id);

    List<CarDTO> searchCar(SearchDTO searchDTO);

    User checkUserName(UserDTO userDTO);

    void register(UserDTO userDTO);

    void rentCar(BookingDTO bookingDTO);
}
