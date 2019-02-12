package com.vrcserver.vrc.controllers;

import com.vrcserver.vrc.dao.models.*;
import com.vrcserver.vrc.dto.*;
import com.vrcserver.vrc.services.AdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "admin-login")
    public ModelAndView login(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") != null) {
            mav.setViewName("redirect:/admin");
        } else {
            mav.addObject("user", new UserDTO());
            mav.setViewName("admin/login");
        }
        return mav;
    }

    @PostMapping(value = "/admin-loginProcess")
    public ModelAndView getLogin(UserDTO userDTO, HttpSession session)  {
        ModelAndView mav= new ModelAndView();
        mav.addObject("user",userDTO = adminService.login(userDTO));
        if (userDTO.getId() != null) {
            session.setAttribute("user", userDTO);
            session.removeAttribute("error");
            mav.setViewName("redirect:/admin");
        } else {
            session.setAttribute("error", userDTO.getMessage());
            mav.addObject("user", new UserDTO());
            mav.setViewName("admin/login");
        }
        return mav;
    }
    @GetMapping(value = "logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        session.removeAttribute("user");
        mav.setViewName("redirect:/admin-login");
        return mav;
    }
    @GetMapping(value = "/admin")
    public ModelAndView getIndex(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.setViewName("admin/index");
        return mav;
    }
    @GetMapping(value = "/admin-listTypeCar")
    public ModelAndView listTypeCar(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("listTypeCar",adminService.getTypeCar());
        mav.setViewName("admin/typeCar");
        return mav;
    }
    @GetMapping(value = "/admin-listCarOwner")
    public ModelAndView listCarOwner(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("listCarOwner",adminService.getCarOwner());
        mav.setViewName("admin/carOwner");
        return mav;
    }
    @GetMapping(value = "/admin-listBooking")
    public ModelAndView getListBooking(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("listBooking",adminService.getBooking());
        mav.setViewName("admin/booking");
        return mav;
    }
    @GetMapping(value = "/admin-listUser")
    public ModelAndView getListUser(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("listUser",adminService.getUser());
        mav.setViewName("admin/user");
        return mav;
    }
    @GetMapping(value = "/admin-listCar")
    public ModelAndView getListCar(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("listCar",adminService.getCar());
        mav.setViewName("admin/car");
        return mav;
    }
    @GetMapping(value = "/removeTypeCar-{id}-delete")
    public ModelAndView deleteTypeCar(@PathVariable(value = "id") Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("list",adminService.removeTypeCar(id));
        mav.setViewName("redirect:/admin-listTypeCar");
        return mav;
    }
    @GetMapping(value = "/removeCar-{id}-delete")
    public ModelAndView deleteCar(@PathVariable(value = "id") Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("list",adminService.removeCar(id));
        mav.setViewName("redirect:/admin-listCar");
        return mav;
    }
    @GetMapping(value = "/removeCarOwner-{id}-delete")
    public ModelAndView deleteCarOwner(@PathVariable(value = "id") Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("list",adminService.removeCarOwner(id));
        mav.setViewName("redirect:/admin-listCarOwner");
        return mav;
    }
    @GetMapping(value = "/removeBooking-{id}-delete")
    public ModelAndView deleteBooking(@PathVariable(value = "id") Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("list",adminService.removeBooking(id));
        mav.setViewName("redirect:/admin-listBooking");
        return mav;
    }
    @GetMapping(value = "/removeUser-{id}-delete")
    public ModelAndView deleteUser(@PathVariable(value = "id") Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("list",adminService.removeUser(id));
        mav.setViewName("redirect:/admin-listUser");
        return mav;
    }
    @GetMapping(value = "/admin-addTypeCar")
    public ModelAndView getTypeCar(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("add",new TypeCarDTO());
        mav.setViewName("admin/addTypeCar");
        return mav;
    }
    @PostMapping(value = "/admin-addTypeCar-Process")
    public ModelAndView getTypeCarProcess(TypeCarDTO typeCarDTO,HttpSession session){
        ModelAndView mav = new ModelAndView();
        TypeCar check = adminService.checkTypeCarName(typeCarDTO);
        if (check.getId() != null) {
            typeCarDTO.setMessage("Type Car Name đã tồn tại");
            session.setAttribute("error", typeCarDTO.getMessage());
            mav.setViewName("redirect:/admin-addTypeCar");
        }
        else {
            mav.addObject("add", adminService.addTypeCar(typeCarDTO));
            mav.setViewName("redirect:/admin-listTypeCar");
        }
        return mav;
    }
    @GetMapping(value = "/admin-addCarOwner")
    public ModelAndView getCarOwner(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("add",new CarOwnerDTO());
        mav.addObject("listUser",adminService.getUser());
        mav.setViewName("admin/addCarOwner");
        return mav;
    }
    @PostMapping(value = "/admin-addCarOwner-Process")
    public ModelAndView getCarOwnerProcess(CarOwnerDTO carOwnerDTO){
        ModelAndView mav = new ModelAndView();
        mav.addObject("add",adminService.addCarOwner(carOwnerDTO));
        mav.setViewName("redirect:/admin-listCarOwner");
        return mav;
    }
    @GetMapping(value = "/admin-addCar")
    public ModelAndView getAddCar(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("add",new CarDTO());
        mav.addObject("listTypeCar",adminService.getTypeCar());
        mav.addObject("listCarOwner",adminService.getCarOwner());
        mav.setViewName("admin/addCar");
        return mav;
    }
    @PostMapping(value = "/admin-addCar-Process")
    public ModelAndView getCarProcess(CarDTO carDTO){
        ModelAndView mav = new ModelAndView();
        mav.addObject("add",adminService.addCar(carDTO));
        mav.setViewName("redirect:/admin-listCar");
        return mav;
    }
    @GetMapping(value = "/admin-addUser")
    public ModelAndView getAddUser(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("add",new UserDTO());
        mav.setViewName("admin/addUser");
        return mav;
    }
    @PostMapping(value = "/admin-addUser-Process")
    public ModelAndView getUserProcess(UserDTO userDTO){
        ModelAndView mav = new ModelAndView();
        mav.addObject("add",adminService.addUser(userDTO));
        mav.setViewName("redirect:/admin-listUser");
        return mav;
    }
//    @GetMapping(value = "/admin-addBooking")
//    public ModelAndView getAddBooking(HttpSession session){
//        ModelAndView mav = new ModelAndView();
//        if (session.getAttribute("user") == null){
//            mav.setViewName("redirect:/admin-login");
//            return mav;
//        }
//        mav.addObject("add",new BookingDTO());
//        mav.addObject("listUser",adminService.getUser());
//        mav.addObject("listCar",adminService.getCar());
//        mav.setViewName("admin/addBooking");
//        return mav;
//    }
    @GetMapping(value = "/admin-addBooking")
    public ModelAndView getAddBooking(HttpSession session){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        mav.addObject("add",new BookingDTO());
        mav.addObject("listUser",adminService.getUser());
        mav.addObject("listCar",adminService.getCar());
        mav.setViewName("admin/addBooking");
        return mav;
    }
    @PostMapping(value = "/admin-addBooking-Process")
    public ModelAndView getBookingProcess(BookingDTO bookingDTO){
        ModelAndView mav = new ModelAndView();
        mav.addObject("add",adminService.addBooking(bookingDTO));
        mav.setViewName("redirect:/admin-listBooking");
        return mav;
    }
    @GetMapping(value = "/admin-detailTypeCar-{id}")
    public ModelAndView detailTypeCar(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        TypeCar typeCar = adminService.findTypeCar(id);
        mav.addObject("edit",typeCar);

        mav.setViewName("admin/detailTypeCar");
        return mav;
    }
    @GetMapping(value = "/admin-updateTypeCar-{id}")
    public ModelAndView updateTypeCar(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        TypeCar typeCar = adminService.findTypeCar(id);
        if(typeCar != null){
            mav.addObject("edit",typeCar);
        }

        mav.setViewName("admin/editTypeCar");
        return mav;
    }

    @PostMapping(value = "/admin-updateTypeCar-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateTypeCarProcess( TypeCarDTO typeCarDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateTypeCar(typeCarDTO);
        mav.setViewName("redirect:/admin-listTypeCar");
        return mav;
    }
    @GetMapping(value = "/admin-detailCar-{id}")
    public ModelAndView detailCar(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Car car = adminService.findCar(id);
        mav.addObject("listTypeCar",adminService.getTypeCar());
        mav.addObject("listCarOwner",adminService.getCarOwner());
        mav.addObject("edit",car);

        mav.setViewName("admin/detailCar");
        return mav;
    }
    @GetMapping(value = "/admin-updateCar-{id}")
    public ModelAndView updateCar(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Car car = adminService.findCar(id);
        mav.addObject("listTypeCar",adminService.getTypeCar());
        mav.addObject("listCarOwner",adminService.getCarOwner());
        if(car != null){
            mav.addObject("edit",car);
        }

        mav.setViewName("admin/editCar");
        return mav;
    }

    @PostMapping(value = "/admin-updateCar-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateCarProcess( CarDTO carDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateCar(carDTO);
        mav.setViewName("redirect:/admin-listCar");
        return mav;
    }
    @GetMapping(value = "/admin-detailCarOwner-{id}")
    public ModelAndView detailCarOwner(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        CarOwner carOwner = adminService.findCarOwner(id);
        mav.addObject("listUser",adminService.getUser());
        mav.addObject("edit",carOwner);

        mav.setViewName("admin/detailCarOwner");
        return mav;
    }
    @GetMapping(value = "/admin-updateUser-{id}")
    public ModelAndView updateUser(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        User user = adminService.findUser(id);
        if(user != null){
            mav.addObject("edit",user);
        }

        mav.setViewName("admin/editUser");
        return mav;
    }

    @PostMapping(value = "/admin-updateUser-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateUserProcess( UserDTO userDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateUser(userDTO);
        mav.setViewName("redirect:/admin-listUser");
        return mav;
    }
    @GetMapping(value = "/admin-detailUser-{id}")
    public ModelAndView detailUser(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        User user = adminService.findUser(id);
        mav.addObject("edit",user);

        mav.setViewName("admin/detailUser");
        return mav;
    }
    @GetMapping(value = "/admin-updateCarOwner-{id}")
    public ModelAndView updateCarOwner(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        CarOwner carOwner = adminService.findCarOwner(id);
        mav.addObject("listUser",adminService.getUser());
        if(carOwner != null){
            mav.addObject("edit",carOwner);
        }

        mav.setViewName("admin/editCarOwner");
        return mav;
    }

    @PostMapping(value = "/admin-updateCarOwner-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateCarOwnerProcess( CarOwnerDTO carOwnerDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateCarOwner(carOwnerDTO);
        mav.setViewName("redirect:/admin-listCarOwner");
        return mav;
    }
    @GetMapping(value = "/admin-detailBooking-{id}")
    public ModelAndView detailBooking(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }
        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Booking booking = adminService.findBooking(id);
        mav.addObject("listUser",adminService.getUser());
        mav.addObject("listCar",adminService.getCar());
        mav.addObject("edit",booking);

        mav.setViewName("admin/detailBooking");
        return mav;
    }
    @GetMapping(value = "/admin-updateBooking-{id}")
    public ModelAndView updateBooking(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("user") == null){
            mav.setViewName("redirect:/admin-login");
            return mav;
        }

        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
        Booking booking = adminService.findBooking(id);
        mav.addObject("listUser",adminService.getUser());
        mav.addObject("listCar",adminService.getCar());
        if(booking != null){
            mav.addObject("edit",booking);
        }

        mav.setViewName("admin/editBooking");
        return mav;
    }

    @PostMapping(value = "/admin-updateBooking-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
    public ModelAndView updateBookingProcess( BookingDTO bookingDTO){
        ModelAndView mav = new ModelAndView();
        adminService.updateBooking(bookingDTO);
        mav.setViewName("redirect:/admin-listBooking");
        return mav;
    }
//    @GetMapping(value = "/admin-updateBooking-{id}")
//    public ModelAndView updateUser(HttpSession session,@PathVariable(value = "id") Long id/*  ở đây chỉ lấy thông tin ra thôi chứ chưa phải cần -> nên nó báo bad Request
//    ,sau đó ra đây để gọi nó ra và modelandview qua jsp*/){
//        ModelAndView mav = new ModelAndView();
//        if (session.getAttribute("user") == null){
//            mav.setViewName("redirect:/admin-login");
//            return mav;
//        }
//
//        //Giờ muốn kiểm tra có lấy đc hay k mình thêm check: về nguyên tắc thì phải lấy được dữ liệu mới  model qua
//        Booking booking = adminService.findBooking(id);
//        if(booking != null){
//            mav.addObject("edit",booking);
//        }
//
//        mav.setViewName("admin/editBooking");
//        return mav;
//    }
//
//    @PostMapping(value = "/admin-updateBooking-process")//rồi chặn ở dòng đầu-> run->debug server-> nhấnn nút F8 , muốn kết thúc debug thì F10
//    public ModelAndView updateProcess( BookingDTO bookingDTO){
//        ModelAndView mav = new ModelAndView();
//        adminService.updateBooking(bookingDTO);
//        mav.setViewName("redirect:/admin-listBooking");
//        return mav;
//    }

    //--------------------------------- Type Car ----------------------------------//
//    @GetMapping(value = "/getTypeCar")
//    public Response<List<TypeCarDTO>> getTypeCar() {
//        List<TypeCarDTO> typeCarDTOList = adminService.getTypeCar();
//        return new Response<>(true, typeCarDTOList, "List Successful !");
//    }
//    @GetMapping(value = "/typeCar/{id}/delete")
//    public Response<TypeCarDTO> removeTypeCar(@PathVariable(value = "id") Long id) {
//        TypeCarDTO typeCarDTO = adminService.removeTypeCar(id);
//        return new Response<>(true, typeCarDTO, "Successful remove");
//    }
//    @PostMapping(value = "/addTypeCar")
//    public Response<TypeCarDTO> addTypeCar(@RequestBody TypeCarDTO typeCarDTO){
//        typeCarDTO = adminService.addTypeCar(typeCarDTO);
//        return new Response<>(true,typeCarDTO,"Add successful !");
//    }
//
//
//    //--------------------------------- Car ---------------------------------------//
//    @GetMapping(value = "/getCar")
//    public Response<List<CarDTO>> getCar() {
//        List<CarDTO> carDTOList = adminService.getCar();
//        return new Response<>(true, carDTOList, "List Successful !");
//    }
//    @GetMapping(value = "/car/{id}/delete")
//    public Response<CarDTO> removeCar(@PathVariable(value = "id") Long id) {
//        CarDTO carDTO = adminService.removeCar(id);
//        return new Response<>(true, carDTO, "Successful remove");
//    }
//    @PostMapping(value = "/addCar")
//    public Response<CarDTO> addCar(@RequestBody CarDTO carDTO){
//        carDTO = adminService.addCar(carDTO);
//        return new Response<>(true,carDTO,"Add successful !");
//    }
////    @GetMapping(value = ("/updateCar-{id}"))
////    public Response<CarDTO> updateCar(@PathVariable(value = "id") Long id, CarDTO car){
////        car = adminService.updateCar(id);
////        return new Response<>(true, car,"Edit successful !");
////    }
//
//
//    //--------------------------------- Car Owner ---------------------------------//
//    @GetMapping(value = "/getCarOwner")
//    public Response<List<CarOwnerDTO>> getCarOwner() {
//        List<CarOwnerDTO> carOwnerDTOList = adminService.getCarOwner();
//        return new Response<>(true, carOwnerDTOList, "List Successful !");
//    }
//    @GetMapping(value = "/carOwner/{id}/delete")
//    public Response<CarOwnerDTO> removeCarOwner(@PathVariable(value = "id") Long id) {
//        CarOwnerDTO carOwnerDTO = adminService.removeCarOwner(id);
//        return new Response<>(true, carOwnerDTO, "Successful remove");
//    }
//    @PostMapping(value = "/addCarOwner")
//    public Response<CarOwnerDTO> addCarOwner(@RequestBody CarOwnerDTO carOwnerDTO){
//        carOwnerDTO = adminService.addCarOwner(carOwnerDTO);
//        return new Response<>(true, carOwnerDTO,"Add successful !");
//    }
//
//    //--------------------------------- Booking ---------------------------------//
//    @GetMapping(value = "/getBooking")
//    public Response<List<BookingDTO>> getBooking() {
//        List<BookingDTO> bookingDTOList = adminService.getBooking();
//        return new Response<>(true, bookingDTOList, "List Successful !");
//    }
//    @GetMapping(value = "/booking/{id}/delete")
//    public Response<BookingDTO> removeBooking(@PathVariable(value = "id") Long id) {
//        BookingDTO bookingDTO = adminService.removeBooking(id);
//        return new Response<>(true, bookingDTO, "Successful remove");
//    }
//    @PostMapping(value = "/addBooking")
//    public Response<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO){
//        bookingDTO = adminService.addBooking(bookingDTO);
//        return new Response<>(true, bookingDTO,"Add successful !");
//    }
//
//    //--------------------------------- User ---------------------------------//
//    @GetMapping(value = "/getUser")
//    public Response<List<UserDTO>> getUser() {
//        List<UserDTO> userDTOList = adminService.getUser();
//        return new Response<>(true, userDTOList, "List Successful !");
//    }
//    @GetMapping(value = "/user/{id}/delete")
//    public Response<UserDTO> removeUser(@PathVariable(value = "id") Long id) {
//        UserDTO userDTO = adminService.removeUser(id);
//        return new Response<>(true, userDTO, "Successful remove");
//    }
//    @PostMapping(value = "/addUser")
//    public Response<UserDTO> addUser(@RequestBody UserDTO userDTO){
//        userDTO = adminService.addUser(userDTO);
//        return new Response<>(true, userDTO,"Add successful !");
//    }
}
