package com.dish.sb.sptdd.respository;

import com.dish.sb.sptdd.model.Employee;
import com.dish.sb.sptdd.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    public void givenEmployee_whenSaved_thenreturnsavedemployee() {

        Employee emp = Employee.builder().email("saf@gmail.com")
                .firstname("safiq").lastname("bari").build();

        Employee savedemp = employeeRepository.save(emp);

        Assertions.assertThat(savedemp).isNotNull();

    }
}
