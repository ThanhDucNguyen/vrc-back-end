package com.vrcserver.vrc.services;

import com.vrcserver.vrc.dao.models.Booking;
import com.vrcserver.vrc.dao.models.Car;
import com.vrcserver.vrc.dao.models.TypeCar;
import com.vrcserver.vrc.dao.models.User;
import com.vrcserver.vrc.dao.repositories.BookingRepository;
import com.vrcserver.vrc.dao.repositories.CarRepository;
import com.vrcserver.vrc.dao.repositories.TypeCarRepository;
import com.vrcserver.vrc.dao.repositories.UserRepository;
import com.vrcserver.vrc.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VrcServiceImpl implements VrcService {

    @Autowired
    private JavaMailSender sender;

    private UserRepository userRepository;
    private TypeCarRepository typeCarRepository;
    private CarRepository carRepository;
    private BookingRepository bookingRepository;

    public VrcServiceImpl(UserRepository userRepository, TypeCarRepository typeCarRepository, CarRepository carRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.typeCarRepository = typeCarRepository;
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = userRepository.findByUserNameAndPassword(userDTO.getUserName(), userDTO.getPassword());
        if (user != null) {
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setUserPhone(user.getUserPhone());
            userDTO.setUserEmail(user.getUserEmail());
        }
        return userDTO;
    }

    @Override
    public User checkUserName(UserDTO userDTO) {
        return userRepository.checkUserName(userDTO.getUserName());
    }

    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        user.setUserEmail(userDTO.getUserEmail());
        user.setUserName(userDTO.getUserName());
        user.setUserPhone(userDTO.getUserPhone());
        user.setPassword(userDTO.getPassword());
        user.setRole(2);
        userRepository.save(user);
    }

    @Override
    public List<TypeCarDTO> listTypeCar() {
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
    public List<CarDTO> listCar(Long id) {
        List<CarDTO> carDTOArrayList = new ArrayList<>();
        Optional<TypeCar> typeCarOptional = typeCarRepository.findById(id);
        for (Car car : typeCarOptional.get().getCars()) {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setPrice(car.getPrice());
            carDTO.setType(car.getType());
            carDTO.setYearCar(car.getYearCar());


            CarOwnerDTO carOwnerDTO = new CarOwnerDTO();
            carOwnerDTO.setId(car.getCarOwner().getId());
            carOwnerDTO.setOwnerName(car.getCarOwner().getOwnerName());
            carOwnerDTO.setOwnerAddress(car.getCarOwner().getOwnerAddress());
            carOwnerDTO.setOwnerEmail(car.getCarOwner().getOwnerEmail());
            carOwnerDTO.setOwnerPhone(car.getCarOwner().getOwnerPhone());

            carDTO.setCarOwner(carOwnerDTO);
            carDTOArrayList.add(carDTO);
        }
        return carDTOArrayList;
    }

    @Override
    public List<CarDTO> searchCar(SearchDTO searchDTO) {
//        String a = searchDTO.getName();
//        String b = searchDTO.getTypeCar();
//        String c = searchDTO.getModel();
//        String d = searchDTO.getYearCar();
        List<Car> cars = carRepository.getCar(searchDTO.getTypeCar(), searchDTO.getYearCar(), searchDTO.getModel(), searchDTO.getName());
        List<CarDTO> carDTOS = new ArrayList<>();
        for (Car car : cars) {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setModel(car.getModel());
            carDTO.setPrice(car.getPrice());
            carDTO.setType(car.getType());
            carDTO.setYearCar(car.getYearCar());


            CarOwnerDTO carOwnerDTO = new CarOwnerDTO();
            carOwnerDTO.setId(car.getCarOwner().getId());
            carOwnerDTO.setOwnerName(car.getCarOwner().getOwnerName());
            carOwnerDTO.setOwnerAddress(car.getCarOwner().getOwnerAddress());
            carOwnerDTO.setOwnerEmail(car.getCarOwner().getOwnerEmail());
            carOwnerDTO.setOwnerPhone(car.getCarOwner().getOwnerPhone());

            carDTO.setCarOwner(carOwnerDTO);
            carDTOS.add(carDTO);

        }
        return carDTOS;
    }

    @Override
    public void rentCar(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setPay(bookingDTO.getPay());
        booking.setPrice(bookingDTO.getPrice());
        booking.setRentalDay(bookingDTO.getRentalDay());
        booking.setReturnDay(bookingDTO.getReturnDay());
        Optional<User> userOptional = userRepository.findById(bookingDTO.getUser().getId());
        User user;
        if (!userOptional.isPresent()) {
            return;
        } else {
            User getUser = userOptional.get();
            user = userRepository.save(getUser);
        }
        Optional<Car> carOptional = carRepository.findById(bookingDTO.getCar().getId());
        Car car;
        if (carOptional.isPresent()) {
            Car getCar = carOptional.get();
            car = carRepository.save(getCar);
        } else {
            return;
        }
        booking.setUser(user);
        booking.setCar(car);
        Booking saveBooking = bookingRepository.save(booking);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {

            helper.setTo(saveBooking.getUser().getUserEmail());
            helper.setText("\nHi: " + saveBooking.getUser().getUserName() +
                    "\nPhone: " + saveBooking.getUser().getUserPhone() +
                    "\nEmail: " + saveBooking.getUser().getUserPhone() +
                    "\nYou have successfully rented a car" +
                    "\nManufacturer Car: " + saveBooking.getCar().getTypeCar().getName() +
                    "\nModel Car: " + saveBooking.getCar().getModel() +
                    "\nYear Car: " + saveBooking.getCar().getYearCar() +
                    "\nType Car: " + saveBooking.getCar().getType() +
                    "\nRental Day: " + saveBooking.getRentalDay() +
                    " ---> Return Day: " + saveBooking.getReturnDay() +
                    "\nPrice: " + saveBooking.getPrice() +
                    "\nPay: " + saveBooking.getPay() +
                    "\n-------------------------------------------" +
                    "\nTHANK YOU FOR READING THE LETTER"
            );
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

    }
}
