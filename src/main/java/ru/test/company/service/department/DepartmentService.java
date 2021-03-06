package ru.test.company.service.department;

import ru.test.company.model.department.Department;
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
    Department getByName(String department_name);
    Long getCountDaysInCompany(UUID id);
}
