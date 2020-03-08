package ru.test.company.repository.ShareRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.test.company.model.BaseEntity;
import ru.test.company.model.position.Position;

import java.util.UUID;

@Repository
public interface ShareRepository {
    @Query(nativeQuery = true, countQuery = "SELECT count(*) FROM employee LEFT JOIN calendar ON employee.id = calendar.employee_id WHERE presence_at_work = true AND department_id = ?")
    Integer getCountAtWorkEmployeeInDepartment(UUID departmentId);

}
