package ru.test.company.service.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.company.model.department.Department;
import ru.test.company.repository.department.DepartmentRepository;
import ru.test.company.service.department.argument.DepartmentCreateArgument;
import ru.test.company.service.department.argument.DepartmentUpdateArgument;

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
    public Department createDepartment(DepartmentCreateArgument departmentCreateArgument) {
        return departmentRepository.save(Department.builder()
                .name(departmentCreateArgument.getName())
                .firstWorkingDate(departmentCreateArgument.getFirstWorkingDate())
                .lastWorkingDate(departmentCreateArgument.getLastWorkingDate())
                .build());
    }

    @Override
    public Department updateDepartment(UUID departmentId, DepartmentUpdateArgument departmentUpdateArgument) {
        return departmentRepository.save(Department.builder()
                .name(departmentUpdateArgument.getName())
                .firstWorkingDate(departmentUpdateArgument.getFirstWorkingDate())
                .lastWorkingDate(departmentUpdateArgument.getLastWorkingDate())
                .build());
    }

    @Override
    public void removeDepartment(UUID departmentId) {
        departmentRepository.delete(departmentRepository.getOne(departmentId));
    }

    @Override
    public Department getExisting(UUID departmentId) {
        return departmentRepository.getOne(departmentId);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getByName(String department_name) {
        return departmentRepository.getDepartmentByName(department_name);
    }
}
