package ru.test.company.api.position.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import ru.test.company.api.position.dto.out.PositionDto;
import ru.test.company.model.position.Position;

import java.util.List;

@Mapper
public interface PositionMapper {

    Position toEntity(PositionDto dto);

    PositionDto toDto(Position entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<PositionDto> toDtoListFromDB(List<Position> entityList);
}


