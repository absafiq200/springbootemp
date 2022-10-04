package com.dish.sb.sptdd.service;

import com.dish.sb.sptdd.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
}
