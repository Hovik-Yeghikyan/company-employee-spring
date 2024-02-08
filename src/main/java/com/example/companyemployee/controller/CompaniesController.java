package com.example.companyemployee.controller;

import com.example.companyemployee.entity.Category;
import com.example.companyemployee.entity.Company;
import com.example.companyemployee.repository.CompanyRepository;
import com.example.companyemployee.security.SpringUser;
import com.example.companyemployee.service.CategoryService;
import com.example.companyemployee.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompaniesController {


    private final CompanyService companyService;
    private final CategoryService categoryService;


    @GetMapping("/companies")
    public String companiesPage(ModelMap modelMap) {
        List<Company> companies = companyService.findAll();
        modelMap.put("companies", companies);
        return "companies";
    }

    @GetMapping("/companies/add")
    public String addCompanyPage(ModelMap modelMap) {
        modelMap.addAttribute("categories",categoryService.findAll());
        return "addCompany";
    }

    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute Company company, @AuthenticationPrincipal SpringUser springUser) {
        company.setUser(springUser.getUser());
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        companyService.delete(id);
        return "redirect:/companies";
    }
}