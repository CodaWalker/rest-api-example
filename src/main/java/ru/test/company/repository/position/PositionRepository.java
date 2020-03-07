package ru.test.company.repository.position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.company.model.position.Position;

import java.util.UUID;

@Repository
public interface PositionRepository extends JpaRepository<Position, UUID> {
    Position getPositionByName(String name);

}
