package com.vrcserver.vrc.controllers;

import com.vrcserver.vrc.dto.*;
import com.vrcserver.vrc.dto.response.Response;
import com.vrcserver.vrc.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/login-admin")
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
    @GetMapping(value = "/typeCar/{id}/delete")
    public Response<TypeCarDTO> removeTypeCar(@PathVariable(value = "id") Long id) {
        TypeCarDTO typeCarDTO = adminService.removeTypeCar(id);
        return new Response<>(true, typeCarDTO, "Successful remove");
    }
    @PostMapping(value = "/addTypeCar")
    public Response<TypeCarDTO> addTypeCar(@RequestBody TypeCarDTO typeCarDTO){
        typeCarDTO = adminService.addTypeCar(typeCarDTO);
        return new Response<>(true,typeCarDTO,"Add successful !");
    }

    //--------------------------------- Car ---------------------------------------//
    @GetMapping(value = "/getCar")
    public Response<List<CarDTO>> getCar() {
        List<CarDTO> carDTOList = adminService.getCar();
        return new Response<>(true, carDTOList, "List Successful !");
    }
    @GetMapping(value = "/car/{id}/delete")
    public Response<CarDTO> removeCar(@PathVariable(value = "id") Long id) {
        CarDTO carDTO = adminService.removeCar(id);
        return new Response<>(true, carDTO, "Successful remove");
    }
    @PostMapping(value = "/addCar")
    public Response<CarDTO> addCar(@RequestBody CarDTO carDTO){
        carDTO = adminService.addCar(carDTO);
        return new Response<>(true,carDTO,"Add successful !");
    }

    //--------------------------------- Car Owner ---------------------------------//
    @GetMapping(value = "/getCarOwner")
    public Response<List<CarOwnerDTO>> getCarOwner() {
        List<CarOwnerDTO> carOwnerDTOList = adminService.getCarOwner();
        return new Response<>(true, carOwnerDTOList, "List Successful !");
    }
    @GetMapping(value = "/carOwner/{id}/delete")
    public Response<CarOwnerDTO> removeCarOwner(@PathVariable(value = "id") Long id) {
        CarOwnerDTO carOwnerDTO = adminService.removeCarOwner(id);
        return new Response<>(true, carOwnerDTO, "Successful remove");
    }
    @PostMapping(value = "/addCarOwner")
    public Response<CarOwnerDTO> addCarOwner(@RequestBody CarOwnerDTO carOwnerDTO){
        carOwnerDTO = adminService.addCarOwner(carOwnerDTO);
        return new Response<>(true, carOwnerDTO,"Add successful !");
    }

    //--------------------------------- Booking ---------------------------------//
    @GetMapping(value = "/getBooking")
    public Response<List<BookingDTO>> getBooking() {
        List<BookingDTO> bookingDTOList = adminService.getBooking();
        return new Response<>(true, bookingDTOList, "List Successful !");
    }
    @GetMapping(value = "/booking/{id}/delete")
    public Response<BookingDTO> removeBooking(@PathVariable(value = "id") Long id) {
        BookingDTO bookingDTO = adminService.removeBooking(id);
        return new Response<>(true, bookingDTO, "Successful remove");
    }

    //--------------------------------- User ---------------------------------//
    @GetMapping(value = "/getUser")
    public Response<List<UserDTO>> getUser() {
        List<UserDTO> userDTOList = adminService.getUser();
        return new Response<>(true, userDTOList, "List Successful !");
    }
    @GetMapping(value = "/user/{id}/delete")
    public Response<UserDTO> removeUser(@PathVariable(value = "id") Long id) {
        UserDTO userDTO = adminService.removeUser(id);
        return new Response<>(true, userDTO, "Successful remove");
    }
    @PostMapping(value = "/addUser")
    public Response<UserDTO> addUser(@RequestBody UserDTO userDTO){
        userDTO = adminService.addUser(userDTO);
        return new Response<>(true, userDTO,"Add successful !");
    }
}
