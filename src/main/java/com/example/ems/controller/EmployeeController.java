package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return service.addEmployee(employee);
    }



    @GetMapping
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return service.getAllEmployee(keyword,page,size);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable Long id){
        return service.getEmployeeByID(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        return service.updateEmployee(id,employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        service.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}
