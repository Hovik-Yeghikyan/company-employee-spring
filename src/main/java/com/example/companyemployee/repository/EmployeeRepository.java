package com.example.companyemployee.repository;

import com.example.companyemployee.entity.Company;
import com.example.companyemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByCompany_id(int id);
    void deleteAllByCompany_Id(int id);
}
