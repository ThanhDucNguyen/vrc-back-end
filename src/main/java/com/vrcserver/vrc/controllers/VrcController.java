package com.vrcserver.vrc.controllers;

import com.vrcserver.vrc.dao.models.TypeCar;
import com.vrcserver.vrc.dao.models.User;
import com.vrcserver.vrc.dao.repositories.UserRepository;
import com.vrcserver.vrc.dto.*;
import com.vrcserver.vrc.dto.response.Response;
import com.vrcserver.vrc.services.VrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VrcController {
    @Autowired
    private UserRepository userRepository;

    private VrcService vrcService;

    public VrcController(VrcService vrcService) {
        this.vrcService = vrcService;
    }

    @PostMapping(value = "/checkAccount")
    public Response<UserDTO> checkAccount(@RequestBody UserDTO userDTO) {
        User user = vrcService.checkUserName(userDTO);
        if (user == null) {
            vrcService.register(userDTO);
            return new Response<>(false, null, "Account chưa đúng, muốn tạo hân");
        } else {
            return new Response<>(true, userDTO, "Account đã tồn tại");
        }
    }

    @PostMapping(value = "/login")
    public Response<UserDTO> login(@RequestBody UserDTO userDTO) {
        userDTO = vrcService.login(userDTO);
        if (userDTO.getId() != null) {
            return new Response<>(true, userDTO, "Successful Login");
        } else {
            return new Response<>(false, null, " User not exits");
        }
    }

    @PostMapping(value = "/profile")
    public Response<UserDTO> profile(@RequestBody UserDTO userDTO) {
        userDTO = vrcService.login(userDTO);
        if (userDTO.getId() != null) {
//            List<BookingDTO> bookingDTOList = vrcService.listTypeCar();
            return new Response<>(true, userDTO, "Successful Login");
        } else {
            return new Response<>(false, null, " User not exits");
        }
    }

    @PostMapping(value = "/register")
    public @ResponseBody
    Response<UserDTO> register(@RequestBody UserDTO userDTO) {
        User user = vrcService.checkUserName(userDTO);
        if (user == null) {
            vrcService.register(userDTO);
            return new Response<>(true, userDTO, "Successful Register");
        } else {
            return new Response<>(false, null, " User not exits");
        }
    }

    @GetMapping(value = "/listTypeCar")
    public Response<List<TypeCarDTO>> listTypeCar() {
        List<TypeCarDTO> typeCarDTOList = vrcService.listTypeCar();
        return new Response<>(true, typeCarDTOList, "List Successful");
    }

    @PostMapping(value = "/listCar/{id}")
    public Response<List<CarDTO>> listCar(@PathVariable(value = "id") Long id) {
        List<CarDTO> carDTOList = vrcService.listCar(id);
        return new Response<>(true, carDTOList, "List Successful");
    }

    @PostMapping(value = "/searchCar")
    public Response<List<CarDTO>> searchCar(@RequestBody SearchDTO searchDTO) {
        List<CarDTO> carDTOList = vrcService.searchCar(searchDTO);
        return new Response<>(true, carDTOList, "Search Successful");
    }

    @PostMapping(value = "/rentCar")
    public Response<BookingDTO> rentCar(@RequestBody BookingDTO bookingDTO) {
        vrcService.rentCar(bookingDTO);
        return new Response<>(true, bookingDTO, "Rental Car Successful");
    }
}
