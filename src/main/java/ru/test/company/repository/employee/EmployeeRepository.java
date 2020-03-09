package ru.test.company.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.employee.Event;
import ru.test.company.model.position.Position;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query(nativeQuery = true, countQuery = "SELECT COUNT(*) FROM Employee WHERE position_id = ? AND department_id = ? AND presence_at_work = true")
    Integer countAllByPositionIdAndDepartmentIdAndPresenceAtWorkIsTrue(UUID positionId,UUID departmentId);
//
//    @Query(nativeQuery = true, countQuery = "SELECT COUNT(*) FROM  Employee LEFT JOIN calendar ON employee.id = calendar.employee_id WHERE presence_at_work = ? AND department_id = ?")
//    Long getCountAtWorkEmployeeInDepartment(Boolean f, UUID departmentId);

//    @Query(countQuery = "SELECT count(e) FROM Employee e LEFT JOIN Calendar c ON e.id = c.employee.id WHERE  e.presenceAtWork = false AND e.department.id = :dIp AND c.event = 'ABSENTED_HOLIDAY' AND c.startIntervalDate = :start AND c.endIntervalDate = :end")
//    Long countEmployeesByDepartmentIdAndNew(@Param("dIp")UUID departmentId, @Param("start")LocalDate start, @Param("end")LocalDate end);

    @Query(countQuery = "SELECT count(e) FROM  Employee e LEFT JOIN Calendar c ON c.employee.id = e.id WHERE e.presenceAtWork = true AND e.department.id = :dIp")
    Long countAllByDepartmentId( @Param("dIp") UUID departmentId);
}
