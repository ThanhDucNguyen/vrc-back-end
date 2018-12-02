package com.vrcserver.vrc.controllers;

import com.vrcserver.vrc.dto.*;
import com.vrcserver.vrc.dto.response.Response;
import com.vrcserver.vrc.services.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/login")
    public Response<UserDTO> login(@RequestBody UserDTO userDTO) {
        userDTO = adminService.login(userDTO);
        if (userDTO.getId() != null) {
            return new Response<>(true, userDTO, "Successful Login");
        } else {
            return new Response<>(false, null, " User not exits");
        }
    }

    //--------------------------------- Type Car ----------------------------------//
    @GetMapping(value = "/getTypeCar")
    public Response<List<TypeCarDTO>> getTypeCar() {
        List<TypeCarDTO> typeCarDTOList = adminService.getTypeCar();
        return new Response<>(true, typeCarDTOList, "List Successful !");
    }

    //--------------------------------- Car ---------------------------------------//
    @GetMapping(value = "/getCar")
    public Response<List<CarDTO>> getCar() {
        List<CarDTO> carDTOList = adminService.getCar();
        return new Response<>(true, carDTOList, "List Successful !");
    }

    //--------------------------------- Car Owner ---------------------------------//
    @GetMapping(value = "/getCarOwner")
    public Response<List<CarOwnerDTO>> getCarOwner() {
        List<CarOwnerDTO> carOwnerDTOList = adminService.getCarOwner();
        return new Response<>(true, carOwnerDTOList, "List Successful !");
    }

    //--------------------------------- Booking ---------------------------------//
    @GetMapping(value = "/getBooking")
    public Response<List<BookingDTO>> getBooking() {
        List<BookingDTO> bookingDTOList = adminService.getBooking();
        return new Response<>(true, bookingDTOList, "List Successful !");
    }

    //--------------------------------- User ---------------------------------//
    @GetMapping(value = "/getUser")
    public Response<List<UserDTO>> getUser() {
        List<UserDTO> userDTOList = adminService.getUser();
        return new Response<>(true, userDTOList, "List Successful !");
    }
}
