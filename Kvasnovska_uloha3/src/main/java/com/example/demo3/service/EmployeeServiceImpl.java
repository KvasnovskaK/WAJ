package com.example.demo3.service;

import com.example.demo3.dao.EmployeeRepository;
import com.example.demo3.entity.Employee;
import com.example.demo3.exception.EmailAlreadyExistsException;
import com.example.demo3.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getSortedEmployees() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Zamestnanec", id));
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {

        if (employee.getId() == null) {
            // CREATE
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        } else {
            // UPDATE - skontrolujeme email len ak sa zmenil
            Employee original = employeeRepository.findById(employee.getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Zamestnanec", employee.getId()));

            if (!original.getEmail().equals(employee.getEmail())
                    && employeeRepository.existsByEmail(employee.getEmail())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        }

        return employeeRepository.save(employee);
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Zamestnanec", id);
        }
        employeeRepository.deleteById(id);
    }
}
