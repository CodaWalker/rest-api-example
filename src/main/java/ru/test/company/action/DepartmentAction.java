package ru.test.company.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.company.api.department.dto.in.DepartmentCreateDto;
import ru.test.company.model.department.Department;
import ru.test.company.service.department.DepartmentService;
import ru.test.company.service.employee.EmployeeService;

@Component
public class DepartmentAction {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentAction(EmployeeService employeeService, DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Department execute(DepartmentCreateDto dto){
         return null;
    }



}
