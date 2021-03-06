package ru.test.company.api.department.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import ru.test.company.api.department.dto.out.DepartmentDto;
import ru.test.company.model.department.Department;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    Department toEntity(DepartmentDto dto);

    DepartmentDto toDto(Department entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<DepartmentDto> toDtoListFromDB(List<Department> entityList);
}


