package com.example.demo.Thymeleaf.app.controller;


import com.example.demo.Thymeleaf.app.exception.ApplicationException;
import com.example.demo.Thymeleaf.app.models.Users;
import com.example.demo.Thymeleaf.app.repository.UserRepository;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@org.springframework.stereotype.Controller
@Slf4j
public class Controller {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign-up")
    public  String showSignUpPage(Users user, Model model){
        log.info("herer");
        model.addAttribute("user", user);
        return "add-user";
    }
    @PostMapping("/add-user")
    public  String addUser(@Valid Users user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-user";
        }
        model.addAttribute("user", user);
        userRepository.save(user);
        return "redirect:/index";
    }
    @GetMapping("/index")
       public String showUserList(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public  String showUpdateForm(@PathVariable ("id") Long id, Model model){
       Users user = userRepository.findById(id).orElseThrow(()-> new ApplicationException("invalid user id"));
       model.addAttribute("user",user);
            return "update-form";
        }

    @PostMapping("/update/{id}")
    public  String updateUser(@PathVariable ("id") Long id, @Valid Users user, BindingResult result, Model model){
        if (result.hasErrors()){
            user.setId(id);
            return "update-form";
        }
        userRepository.save(user);
        return "redirect:/index";

    }

        @GetMapping("/delete/{id}")
        public  String deleteUser(@PathVariable ("id") Long id,  Model model) {
            Users user = userRepository.findById(id)
                    .orElseThrow(() -> new ApplicationException("Invalid user id" + id));
            userRepository.delete(user);
            return "redirect:/index";

        }
}
