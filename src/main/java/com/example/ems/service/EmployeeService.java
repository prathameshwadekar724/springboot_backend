package com.example.ems.service;

import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee addEmployee(Employee employee){
        return repository.save(employee);
    }


    public Page<Employee> getAllEmployee(String keyword, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findByNameContainingIgnoreCase(keyword,pageable);
    }

    public Employee getEmployeeByID(Long id){
        return repository.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id,Employee updatedEmployee){
        Employee employee = getEmployeeByID(id);

        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());

        return repository.save(employee);
    }

    public void deleteEmployee(Long id){
        repository.deleteById(id);
    }
}
