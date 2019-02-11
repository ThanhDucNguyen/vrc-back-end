package com.vrcserver.vrc.controllers;

import com.vrcserver.vrc.dto.CarDTO;
import com.vrcserver.vrc.dto.UserDTO;
import com.vrcserver.vrc.services.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class WebController {
    private AdminService adminService;

    public WebController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
