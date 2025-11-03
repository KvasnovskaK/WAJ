package com.example.demo3.service;

import com.example.demo3.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    List<Employee> getSortedEmployees();
    Employee findById(Long id);
    Employee save(Employee employee);
    void deleteById(Long id);
}
