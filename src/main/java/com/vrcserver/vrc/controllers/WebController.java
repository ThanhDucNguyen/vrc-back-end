package com.vrcserver.vrc.controllers;

import com.vrcserver.vrc.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class WebController {
    @GetMapping(value = "/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
//    @GetMapping(value = "admin-login")
//    public ModelAndView login(HttpSession session) {
//        ModelAndView mav = new ModelAndView();
//        if (session.getAttribute("user") != null) {
//            mav.setViewName("redirect:/admin");
//        } else {
//            mav.addObject("user", new UserDTO());
//            mav.setViewName("admin/login");
//        }
//
//        return mav;
//    }
//    @PostMapping(value = "/admin-loginProcess")
//    public ModelAndView getLogin(UserDTO userDTO, HttpSession session)  {
//        ModelAndView mav= new ModelAndView();
//        mav.addObject("user",userDTO = adminService.login(userDTO));
//        if (userDTO.getId() != null) {
//            session.setAttribute("user", userDTO);
//            session.removeAttribute("error");
//            mav.setViewName("redirect:/admin");
//        } else {
//            mav.addObject("user", new UserDTO());
//            mav.setViewName("admin/login");
//        }
//        return mav;
//    }
//    @GetMapping(value = "logout")
//    public ModelAndView logout(HttpSession session) {
//        ModelAndView mav = new ModelAndView();
//        session.removeAttribute("user");
//        mav.setViewName("redirect:/admin/login");
//        return mav;
//    }
}
