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
    @Query("SELECT count(e) FROM Employee e WHERE e.position.id = :pId AND  e.department.id = :dIp")
    Integer countAllByPositionIdAndDepartmentId(@Param("pId") UUID positionId,@Param("dIp") UUID departmentId);

    @Query("SELECT count(e) FROM Employee e LEFT JOIN Calendar c ON e.id = c.employee.id WHERE e.department.id = :dIp AND c.event = :event AND c.startIntervalDate <= :start AND c.endIntervalDate >= :end")
    Long countAllByDepartmentIdIncludeInterval(@Param("dIp")UUID departmentId,@Param("event")Event event, @Param("start")LocalDate start, @Param("end")LocalDate end);

    @Query("SELECT count(e) FROM  Employee e LEFT JOIN Calendar c ON e.id = c.employee.id WHERE  e.department.id = :dIp")
    Long countAllByDepartmentId( @Param("dIp") UUID departmentId);
}
