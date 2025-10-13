package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // --- GET all employees ---
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    // --- GET one employee ---
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        try {
            Employee employee = employeeService.findById(id);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // --- POST create new employee ---
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    // --- PUT update existing employee ---
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee updated) {
        try {
            Employee existing = employeeService.findById(id);

            existing.setFirstName(updated.getFirstName());
            existing.setLastName(updated.getLastName());
            existing.setBirthDate(updated.getBirthDate());
            existing.setEmail(updated.getEmail());
            existing.setPhone(updated.getPhone());
            existing.setJobTitle(updated.getJobTitle());
            existing.setSalary(updated.getSalary());
            existing.setFullTime(updated.isFullTime());

            Employee saved = employeeService.save(existing);
            return ResponseEntity.ok(saved);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // --- DELETE employee ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
