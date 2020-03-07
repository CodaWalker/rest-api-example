package ru.test.company.service.position;

import ru.test.company.model.position.Position;
import ru.test.company.service.position.argument.PositionCreateArgument;

import java.util.List;

public interface PositionService {
    Position createPosition(PositionCreateArgument positionCreateArgument);
    List<Position> getAll();
    Position getByName(String name);
}
