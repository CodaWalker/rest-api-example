package ru.test.company.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.model.department.Department;
import ru.test.company.repository.department.DepartmentRepository;
import ru.test.company.service.department.argument.DepartmentCreateArgument;
import ru.test.company.service.department.argument.DepartmentUpdateArgument;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public Department createDepartment(DepartmentCreateArgument departmentCreateArgument) {
        return departmentRepository.save(Department.builder()
                .name(departmentCreateArgument.getName())
                .firstWorkingDate(departmentCreateArgument.getFirstWorkingDate())
                .lastWorkingDate(departmentCreateArgument.getLastWorkingDate())
                .build());
    }

    @Override
    @Transactional
    public Department updateDepartment(UUID departmentId, DepartmentUpdateArgument departmentUpdateArgument) {
        return departmentRepository.save(Department.builder()
                .name(departmentUpdateArgument.getName())
                .firstWorkingDate(departmentUpdateArgument.getFirstWorkingDate())
                .lastWorkingDate(departmentUpdateArgument.getLastWorkingDate())
                .build());
    }

    @Override
    @Transactional
    public void removeDepartment(UUID departmentId) {
        departmentRepository.delete(departmentRepository.getOne(departmentId));
    }

    @Override
    @Transactional
    public Department getExisting(UUID departmentId) {
        return departmentRepository.getOne(departmentId);
    }

    @Override
    @Transactional
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Department getByName(String department_name) {
        return departmentRepository.getDepartmentByName(department_name);
    }

    @Override
    public Long getCountDaysInCompany(UUID uuid) {
        Department department = getExisting(uuid);
        LocalDateTime localDateTime2 = department.getFirstWorkingDate();
        if(department.getLastWorkingDate() != null){
            return localDateTime2.until(department.getLastWorkingDate(), ChronoUnit.DAYS);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime2.until(localDateTime, ChronoUnit.DAYS);
    }

    @Override
    public Long getAllWorkingEmployeesThisDay(UUID id) {
        return null;
//        return departmentRepository.getCountAtWorkEmployeeInDepartment(id);
    }
}
