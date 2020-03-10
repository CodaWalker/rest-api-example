package ru.test.company.service.employee;

import ru.test.company.api.reports.dto.out.DepartmentReportDto;
import ru.test.company.error.ErrorCustom;
import ru.test.company.model.employee.Employee;
import ru.test.company.service.employee.argument.EmployeeCreateArgument;
import ru.test.company.service.employee.argument.EmployeeUpdateArgument;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee createEmployee(EmployeeCreateArgument argument);
    Employee getExisting(UUID employeeId);
    Employee updateEmployee(UUID uuid, EmployeeUpdateArgument employeeUpdateArgument);
    void removeEmployee(UUID employeeId);
    List<Employee> getAll();
    Employee dismissEmployee(UUID uuid) throws ErrorCustom;
    Employee deleteEmployee(UUID uuid) throws ErrorCustom;
    Long getCountDaysInCompany(UUID uuid);
    Integer getByEmployeesByEmployeeIdAndPositionId(UUID positionId, UUID departmentId);
    Long getReportAllHolidayThisDay(UUID id);
    Long getReportAllMedicalThisDay(UUID id);
    Long getReportAllAbsentedOtherThisDay(UUID id);
    Long getReportAllWorkingThisDay(UUID id);
    Long countAllByDepartmentId(UUID id);
    DepartmentReportDto getReportAll(UUID id);
}
