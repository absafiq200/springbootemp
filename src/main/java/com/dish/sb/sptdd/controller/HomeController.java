package com.dish.sb.sptdd.controller;

import com.dish.sb.sptdd.model.Employee;
import com.dish.sb.sptdd.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/v1")
public class HomeController {
    private EmployeeServiceImpl employeeService;
    private List<Employee> lstEmp = new ArrayList<>();
    @Autowired
    HomeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/createemp")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        //Employee ee = employeeService.saveEmployee(employee);
        lstEmp.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.status(HttpStatus.OK).body(lstEmp);
    }

    @GetMapping("/addemp")
    public ResponseEntity<Employee> addDefaultEmployee() {
        Employee ee = Employee.builder().firstname("first_" + new Random().nextInt())
                .lastname("last_"+new Random().nextInt())
                .email("Email_"+new Random().nextInt()).build();
        lstEmp.add(ee);
        //Employee e1 = employeeService.saveEmployee(ee);
        return ResponseEntity.status(HttpStatus.CREATED).body(ee);
    }
}
