package ru.test.company.api.employee.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.test.company.api.employee.dto.out.EmployeeDto;
import ru.test.company.model.employee.Employee;

import java.util.List;
import java.util.UUID;

@Mapper
public interface EmployeeMapper {
    Employee toEntity(EmployeeDto dto);

    @Mapping(target="department_id", source="entity.department.id")
    @Mapping(target="department_name", source="entity.department.name")
    @Mapping(target="position_id", source="entity.position.id")
    @Mapping(target="position_name", source="entity.position.name")
    EmployeeDto toDto(Employee entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<EmployeeDto> toDtoListFromDB(List<Employee> entityList);
}


