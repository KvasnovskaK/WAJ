package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

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
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(employees, "Zoznam zamestnancov načítaný úspešne"));
    }

    // --- GET one employee ---
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(employee, "Zamestnanec načítaný úspešne"));
    }


    // --- POST create new employee ---
    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(0);
        Employee savedEmployee = employeeService.save(employee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(savedEmployee, "Zamestnanec bol úspešne pridaný"));
    }

    // --- PUT update existing employee ---
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee) {
        employeeService.findById(id); // kontrola existencie (alebo vyhodí ObjectNotFoundException)
        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(updatedEmployee, "Zamestnanec bol úspešne aktualizovaný"));
    }

    // --- DELETE employee ---
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable int id) {
        employeeService.findById(id); // kontrola existencie
        employeeService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(null, "Zamestnanec bol úspešne odstránený"));
    }
}
