package com.cg.in.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Logincontroller {

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/login/validateUser")
    public ModelAndView validateUser(String userType) {
        ModelAndView modelAndView = new ModelAndView();

        if ("admin".equals(userType)) {
            modelAndView.setViewName("redirect:/login/admin/dashboard");
        } else if ("customer".equals(userType)) {
            modelAndView.setViewName("redirect:/login/customer/dashboard");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("error", "Invalid user type");
        }

        return modelAndView;
    }

    @GetMapping("/login/admin/dashboard")
    public ModelAndView showAdminDashboard() {
        return new ModelAndView("admin");
    }

    @GetMapping("/login/customer/dashboard")
    public ModelAndView showCustomerDashboard() {
        return new ModelAndView("customer");
    }
    
    /*
    
    @GetMapping("/admin/createcustomer")
    public ModelAndView showCreateCustomerPage() {
        return new ModelAndView("createcustomer");
    }

    @GetMapping("/admin/createaccount")
    public ModelAndView showCreateAccountPage() {
        return new ModelAndView("createaccount");
    }

    @GetMapping("/admin/deleteaccount")
    public ModelAndView showDeleteAccountPage() {
        return new ModelAndView("deleteaccount");
    }

    @GetMapping("/admin/deletecustomer")
    public ModelAndView showDeleteCustomerPage() {
        return new ModelAndView("deletecustomer");
    }

    @GetMapping("/admin/updatecustomer")
    public ModelAndView showUpdateCustomerPage() {
        return new ModelAndView("updatecustomer");
    }

    @GetMapping("/admin/getallcustomers")
    public ModelAndView showGetAllCustomersPage() {
        return new ModelAndView("getallcustomers");
    }

    @GetMapping("/admin/getallaccounts")
    public ModelAndView showGetAllAccountsPage() {
        return new ModelAndView("getallaccounts");
    }

    @GetMapping("/admin/getaccountbyid")
    public ModelAndView showGetAccountByIdPage() {
        return new ModelAndView("getaccountbyid");
    }

    @GetMapping("/admin/getcustomerbyid")
    public ModelAndView showGetCustomerByIdPage() {
        return new ModelAndView("getcustomerbyid");
    }
    
    */
    
}

