package ru.test.company.api.department.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.test.company.api.department.dto.out.DepartmentDto;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.model.department.Department;
import ru.test.company.model.employee.Employee;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    Department toEntity(DepartmentDto dto);

    DepartmentDto toDto(Department entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<DepartmentDto> toDtoListFromDB(List<Department> entityList);
}


