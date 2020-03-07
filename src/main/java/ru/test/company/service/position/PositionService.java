package ru.test.company.service.position;

import ru.test.company.model.position.Position;
import ru.test.company.service.position.argument.PositionCreateArgument;

import java.util.List;
import java.util.UUID;

public interface PositionService {
    Position createPosition(PositionCreateArgument positionCreateArgument);
    List<Position> getAll();
    Position getByName(String name);

    Position getExisting(UUID id);
}
