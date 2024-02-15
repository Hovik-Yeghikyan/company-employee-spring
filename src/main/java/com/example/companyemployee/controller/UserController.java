package com.example.companyemployee.controller;

import com.example.companyemployee.entity.User;
import com.example.companyemployee.entity.UserRole;
import com.example.companyemployee.repository.UserRepository;
import com.example.companyemployee.security.SpringUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    @GetMapping("/user/register")
    public String userRegister(@RequestParam(value = "msg", required = false) String msg, ModelMap modelMap) {
        if (msg != null && !msg.isEmpty()) {
            modelMap.addAttribute("msg", msg);
        }
        return "register";
    }

    @PostMapping("/user/register")
    public String userRegister(@ModelAttribute User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/user/register?msg=User Registered";
        } else {
            return "redirect:/user/register?msg=Email already in use";
        }
    }

    @GetMapping("/loginPage")
    public String loginPage(@AuthenticationPrincipal SpringUser springUser) {
        if (springUser == null) {
            return "loginPage";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal SpringUser springuser) {
        User user = springuser.getUser();
        if (user.getUserRole() == UserRole.USER) {
            return "redirect:/user/home";
        } else if (user.getUserRole() == UserRole.ADMIN) {
            return "redirect:/admin/home";
        }
        return "redirect:/";
    }

    @GetMapping("/user/home")
    public String userHomePage() {
        return "userHome";
    }

    @GetMapping("/admin/home")
    public String adminHomePage() {
        return "adminHome";
    }
}