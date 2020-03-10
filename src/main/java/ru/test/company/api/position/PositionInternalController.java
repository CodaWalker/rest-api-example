package ru.test.company.api.position;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.test.company.api.position.dto.in.PositionCreateDto;
import ru.test.company.api.position.dto.out.PositionDto;
import ru.test.company.api.position.mapper.PositionMapper;
import ru.test.company.service.position.PositionService;
import ru.test.company.service.position.argument.PositionCreateArgument;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "api/position")
@Api("Внутренний контроллер должности")
public class PositionInternalController {
    private final PositionService positionService;
    private final PositionMapper positionMapper;

    @Autowired
    public PositionInternalController(PositionService positionService, PositionMapper positionMapper) {
        this.positionService = positionService;
        this.positionMapper = positionMapper;
    }


    @ApiOperation("Получить список должностей")
    @GetMapping(value = "/all")
    public List<PositionDto> getAll() {
        return positionMapper.toDtoListFromDB(positionService.getAll());
    }

    @ApiOperation("Получить должность по идентификатору")
    @GetMapping("/{id}")
    public PositionDto get(@PathVariable UUID id) {
        return positionMapper.toDto(positionService.getExisting(id));
    }


    @ApiOperation("Создать должность")
    @PostMapping(value = "/create")
    @ResponseStatus(CREATED)
    public PositionDto create(@RequestBody PositionCreateDto dto) {
            return positionMapper.toDto(
                    positionService.createPosition(
                            PositionCreateArgument.builder()
                            .name(dto.getName())
                            .build()));
    }

}
