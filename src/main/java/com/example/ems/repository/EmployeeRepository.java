package com.example.ems.repository;

import com.example.ems.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
