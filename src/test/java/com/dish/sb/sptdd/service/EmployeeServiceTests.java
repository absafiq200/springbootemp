package com.dish.sb.sptdd.service;

import com.dish.sb.sptdd.model.Employee;
import com.dish.sb.sptdd.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void givenEmployeeOject_whenSavedEmployee_thenReturnEmployee() {
        Employee emp = Employee.builder().firstname("saf").lastname("bari")
                .email("saf@gmail.com")
                .id(1l)
                .build();

        BDDMockito.given(employeeRepository.findByEmail(emp.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(emp)).willReturn(emp);
        System.out.println(emp);
        Employee savedEmp = employeeService.saveEmployee(emp);
        System.out.println(savedEmp);

        Mockito.verify(employeeRepository,Mockito.times(1)).save(emp);

        //Assertions.assertThat(savedEmp.getId()).isNotZero();
    }

    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList() {

        Employee emp = Employee.builder().firstname("saf").lastname("bari")
                .email("saf@gmail.com")
                .id(2l)
                .build();

        Employee emp2 = Employee.builder().firstname("saf").lastname("bari")
                .email("saf@gmail.com")
                .id(2l)
                .build();

        BDDMockito.given(employeeRepository.findAll()).willReturn(Arrays.asList(emp,emp2));

        List<Employee> lstEmp = employeeService.getAllEmployee();

        Assertions.assertThat(lstEmp.size()).isEqualTo(2);

    }
}
