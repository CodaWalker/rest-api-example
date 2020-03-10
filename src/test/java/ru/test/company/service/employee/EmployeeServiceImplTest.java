package ru.test.company.service.employee;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.test.company.model.employee.Employee;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    void createEmployee() {
        EmployeeCreateArgument argument = EmployeeCreateArgument.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .firstWorkingDate(LocalDateTime.now())
                .position(null)
                .department(null)
                .build();
        employeeService.createEmployee(argument);
    }
}