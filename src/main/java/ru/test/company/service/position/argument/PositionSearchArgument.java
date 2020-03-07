package ru.test.company.service.position.argument;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class PositionSearchArgument {
    private final String name;
    private final UUID uuid;
}

