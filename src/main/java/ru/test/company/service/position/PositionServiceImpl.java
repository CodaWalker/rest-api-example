package ru.test.company.service.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.company.model.position.Position;
import ru.test.company.repository.position.PositionRepository;
import ru.test.company.service.position.argument.PositionCreateArgument;

import java.util.List;
import java.util.UUID;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    @Transactional
    public Position createPosition(PositionCreateArgument positionCreateArgument) {
        return positionRepository.save(
                Position.builder()
                .name(positionCreateArgument.getName())
                .build());
    }

    @Override
    @Transactional
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    @Transactional
    public Position getByName(String name) {
        return positionRepository.getPositionByName(name);
    }

    @Override
    @Transactional
    public Position getExisting(UUID id) {
        return positionRepository.getOne(id);
    }
}
