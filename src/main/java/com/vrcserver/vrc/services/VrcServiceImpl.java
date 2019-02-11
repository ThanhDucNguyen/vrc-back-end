package com.vrcserver.vrc.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import java.util.Calendar;
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
        User user = userRepository.findByUserNameAndPassword(userDTO.getUserEmail(), userDTO.getPassword());
        if (user != null) {
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setUserEmail(user.getUserEmail());

//            List<BookingDTO> listBooking = new ArrayList<>();
//            Optional<User> users = userRepository.findById(user.getId());
//            for (Booking booking:users.get().getBookings()){
//                BookingDTO bookingDTO = new BookingDTO();
//                bookingDTO.setRentalDay(booking.getRentalDay());
//
//                CarDTO carDTO = new CarDTO();
//                carDTO.setModel(booking.getCar().getModel());
//
//                bookingDTO.setCar(carDTO);
//
//                listBooking.add(bookingDTO);
//            }
        }
        return userDTO;
    }

    @Override
    public User checkUserName(UserDTO userDTO) {
        return userRepository.checkUserName(userDTO.getUserEmail());
    }

    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        user.setUserEmail(userDTO.getUserEmail());
        String a = userDTO.getUserEmail().replace("@gmail.com", "").toLowerCase();
        user.setPassword(a+""+userDTO.getNumberPass());
        user.setRole(1);
        userRepository.save(user);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(user.getUserEmail());
            helper.setText("\nVIETNAMRENTALCAR " +
                    "\nChào: " + user.getUserName() +
                    "\nCảm ơn bạn đã sửa dụng ứng dụng của chúng tôi"+
                    "\nMật khẩu của bạn: " + user.getPassword() +
                    "\n-------------------------------------------" +
                    "\nTHANK YOU FOR READING THE LETTER"
            );
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
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
//        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//
//        Date currentDate = new Date();
//        Date date1 = null;
//        Date date2 = null;
//        Double money = null;
//
//        try {
//            date1 = simpleDateFormat.parse(bookingDTO.getRentalDay());
//            date2 = simpleDateFormat.parse(bookingDTO.getReturnDay());
//
//            long getDiff = date2.getTime() - date1.getTime();
//            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
//
//            Optional<Car> carOptional = carRepository.findById(bookingDTO.getCar().getId());
//            if (carOptional.isPresent()) {
//                Car car = carOptional.get();
//                money = car.getPrice().doubleValue() * getDaysDiff;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
            helper.setTo(saveBooking.getCar().getCarOwner().getOwnerEmail());
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
