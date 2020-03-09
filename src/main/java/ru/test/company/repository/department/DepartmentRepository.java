package ru.test.company.repository.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.test.company.model.department.Department;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Department getDepartmentByName(String name);
//
//    @Query(nativeQuery = true, countQuery = "SELECT COUNT(*) FROM  employee LEFT JOIN calendar ON employee.id = calendar.employee_id WHERE presence_at_work = true AND department_id = ?")
//    Long getCountAtWorkEmployeeInDepartment(UUID departmentId);

//    @Query(nativeQuery = true, countQuery = "SELECT count(*) FROM employee LEFT JOIN calendar ON employee.id = calendar.employee_id WHERE  presence_at_work = false AND department_id = ? AND event = ? AND first_interval_date = ? and last_interval_date = ?")
//    Long getCountNotWorkEmployeeInDepartmentAndPeriod(UUID departmentId, Event event, LocalDate start, LocalDate end);
//

}
