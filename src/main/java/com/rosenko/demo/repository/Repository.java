package com.rosenko.demo.repository;

import com.rosenko.demo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface Repository {
    int save(Employee employee);
    int update(Employee employee);
    int delete(int id);
    Optional<Employee> getEmployeeById(int id);
    List<Employee> getAllEmployees();

}
