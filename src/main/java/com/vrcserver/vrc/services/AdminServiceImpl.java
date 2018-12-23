package com.vrcserver.vrc.services;

import com.vrcserver.vrc.dao.models.*;
import com.vrcserver.vrc.dao.repositories.*;
import com.vrcserver.vrc.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private CarRepository carRepository;
    private TypeCarRepository typeCarRepository;
    private CarOwnerRepository carOwnerRepository;

    public AdminServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, CarRepository carRepository, TypeCarRepository typeCarRepository, CarOwnerRepository carOwnerRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.typeCarRepository = typeCarRepository;
        this.carOwnerRepository = carOwnerRepository;
    }

    @Override //Login admin
    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.loginAdmin(userDTO.getUserName(), userDTO.getPassword());
        if (user != null) {
            userDTO.setId(user.getId());
        }
        return userDTO;
    }

    @Override
    public List<TypeCarDTO> getTypeCar() {
        List<TypeCarDTO> typeCarDTOS = new ArrayList<>();
        List<TypeCar> typeCars = typeCarRepository.findAll();
        for (TypeCar typeCar : typeCars) {
            TypeCarDTO typeCarDTO = new TypeCarDTO();
            typeCarDTO.setId(typeCar.getId());
            typeCarDTO.setName(typeCar.getName());

            typeCarDTOS.add(typeCarDTO);
        }
        return typeCarDTOS;
    }

    @Override
    public TypeCarDTO addTypeCar(TypeCarDTO typeCarDTO) {
        TypeCar typeCar = new TypeCar();
        typeCar.setName(typeCarDTO.getName());
        typeCarRepository.save(typeCar);
        return null;
    }

    @Override
    public List<CarDTO> getCar() {
        List<CarDTO> carDTOS = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setModel(car.getModel());
            carDTO.setType(car.getType());
            carDTO.setYearCar(car.getYearCar());
            carDTO.setPrice(car.getPrice());

            CarOwnerDTO carOwnerDTO = new CarOwnerDTO();
            carOwnerDTO.setOwnerName(car.getCarOwner().getOwnerName());

            carDTO.setCarOwner(carOwnerDTO);
            carDTOS.add(carDTO);
        }
        return carDTOS;
    }

    @Override
    public CarDTO addCar(CarDTO carDTO) {
        Car car = new Car();
        car.setModel(carDTO.getModel());
        car.setPrice(carDTO.getPrice());
        car.setType(carDTO.getType());
        car.setYearCar(carDTO.getYearCar());
        car.setImage(carDTO.getImage());
        Optional<TypeCar> typeCarOptional = typeCarRepository.findById(carDTO.getTypeCar().getId());
        if (typeCarOptional.isPresent()){
            car.setTypeCar(typeCarOptional.get());
        }
        Optional<CarOwner> carOwnerOptional = carOwnerRepository.findById(carDTO.getCarOwner().getId());
        if (carOwnerOptional.isPresent()){
            car.setCarOwner(carOwnerOptional.get());
        }
        carRepository.save(car);
        return null;
    }

    @Override
    public List<CarOwnerDTO> getCarOwner() {
        List<CarOwnerDTO> carOwnerDTOS = new ArrayList<>();
        List<CarOwner> carOwners = carOwnerRepository.findAll();
        for (CarOwner carOwner : carOwners) {
            CarOwnerDTO carOwnerDTO = new CarOwnerDTO();
            carOwnerDTO.setId(carOwner.getId());
            carOwnerDTO.setOwnerName(carOwner.getOwnerName());
            carOwnerDTO.setOwnerEmail(carOwner.getOwnerEmail());
            carOwnerDTO.setOwnerPhone(carOwner.getOwnerPhone());
            carOwnerDTO.setOwnerAddress(carOwner.getOwnerAddress());

            carOwnerDTOS.add(carOwnerDTO);
        }
        return carOwnerDTOS;
    }

    @Override
    public CarOwnerDTO addCarOwner(CarOwnerDTO carOwnerDTO) {
        CarOwner carOwner = new CarOwner();
        carOwner.setOwnerName(carOwnerDTO.getOwnerName());
        carOwner.setOwnerEmail(carOwnerDTO.getOwnerEmail());
        carOwner.setOwnerPhone(carOwnerDTO.getOwnerPhone());
        carOwner.setOwnerAddress(carOwnerDTO.getOwnerAddress());
        carOwnerRepository.save(carOwner);
        return null;
    }

    @Override
    public List<BookingDTO> getBooking() {
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setDate(booking.getBookingDate());
            bookingDTO.setPay(booking.getPay());
            bookingDTO.setPrice(booking.getPrice());
            bookingDTO.setRentalDay(booking.getRentalDay());
            bookingDTO.setReturnDay(booking.getReturnDay());

            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(booking.getUser().getUserName());

            CarDTO carDTO = new CarDTO();
            carDTO.setModel(booking.getCar().getModel());

            bookingDTO.setCar(carDTO);
            bookingDTO.setUser(userDTO);

            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }

    @Override
    public List<UserDTO> getUser() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setUserEmail(user.getUserEmail());
            userDTO.setUserPhone(user.getUserPhone());

            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setUserEmail(userDTO.getUserEmail());
        user.setPassword(userDTO.getPassword());
        user.setUserPhone(userDTO.getUserPhone());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        return null;
    }

    //========================================================DELETE=========================================================//
    @Override
    public TypeCarDTO removeTypeCar(Long id) {
        typeCarRepository.deleteById(id);
        return null;
    }

    @Override
    public CarDTO removeCar(Long id) {
        carRepository.deleteById(id);
        return null;
    }

    @Override
    public CarOwnerDTO removeCarOwner(Long id) {
        carOwnerRepository.deleteById(id);
        return null;
    }

    @Override
    public BookingDTO removeBooking(Long id) {
        bookingRepository.deleteById(id);
        return null;
    }

    @Override
    public UserDTO removeUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }
}
