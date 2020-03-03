package ru.test.company.service.department;

import org.springframework.stereotype.Service;
import ru.test.company.api.department.dto.out.DepartmentDto;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.service.department.argument.DepartmentCreateArgument;
import ru.test.company.service.department.argument.DepartmentUpdateArgument;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department createDepartment(DepartmentCreateArgument departmentCreateArgument);
    Department updateDepartment(UUID departmentId, DepartmentUpdateArgument departmentUpdateArgument);
    void removeDepartment(UUID departmentId);
    Department getExisting(UUID departmentId);
    List<Department> getAll();

    UUID getByName(String department_name);
}
