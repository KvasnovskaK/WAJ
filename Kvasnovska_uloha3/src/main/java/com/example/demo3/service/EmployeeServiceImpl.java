package com.example.demo3.service;

import com.example.demo3.entity.Employee;
import com.example.demo3.dao.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() { return repository.findAll(); }

    @Override
    public Employee findById(Long id) { return repository.findById(id).orElse(null); }

    @Override
    public Employee save(Employee employee) { return repository.save(employee); }

    @Override
    public void deleteById(Long id) { repository.deleteById(id); }
}
