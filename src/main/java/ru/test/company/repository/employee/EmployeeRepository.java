package ru.test.company.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;
import ru.test.company.model.position.Position;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query(nativeQuery = true, countQuery = "SELECT COUNT(*) FROM Employee WHERE position_id = ? AND department_id = ?")
    Integer countAllByPositionIdAndDepartmentId(UUID positionId,UUID departmentId);
}
