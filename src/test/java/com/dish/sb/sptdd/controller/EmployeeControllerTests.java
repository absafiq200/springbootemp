package com.dish.sb.sptdd.controller;

import com.dish.sb.sptdd.model.Employee;
import com.dish.sb.sptdd.service.EmployeeService;
import com.dish.sb.sptdd.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.invocation.MatchersBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import  org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;

@WebMvcTest
public class EmployeeControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeServiceImpl employeeService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenEmployee_whensaved_thenReturnEmployee() throws Exception {
        Employee emp = Employee.builder().firstname("saf").lastname("bari")
                .email("saf@gmail.com")
                .id(1l)
                .build();

        String req = objectMapper.writeValueAsString(emp);

        org.mockito.Mockito.when(employeeService.saveEmployee(emp)).thenReturn(emp);

       // BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class)))
       //         .willAnswer((x)->x.getArgument(0));

        MvcResult mr = this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .post("/api/createemp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Assertions.assertThat(mr.getResponse().getStatus()).isEqualTo(201);
        Mockito.verify(employeeService, Mockito.times(1)).saveEmployee(emp);
    }

    @Test
    public void givenEmployee_whengetAllEmployee_thenReturnAllEmployee()  throws Exception{

        Employee emp = Employee.builder().firstname("saf").lastname("bari")
                .email("saf@gmail.comm")
                .id(1l)
                .build();

        Employee emp2 = Employee.builder().firstname("raf").lastname("abdul bari")
                .email("saf@gmail.comm")
                .id(2l)
                .build();

        Mockito.when(employeeService.getAllEmployee()).thenReturn(Arrays.asList(emp,emp2));

        MvcResult mr = this.mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                .get("/api")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(mr.getResponse().getStatus()).isEqualTo(200);
        Mockito.verify(employeeService,Mockito.times(1)).getAllEmployee();


    }

}
