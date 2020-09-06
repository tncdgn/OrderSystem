package com.project.ordersystem.entity.generator;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

@Component
public class EntityGeneratorFactory {
    public final EnumMap<EntityGeneratorType, EntityGenerator> entityGeneratorEnumMap;

    public EntityGeneratorFactory(List<EntityGenerator> entityGenerators) {
        entityGeneratorEnumMap = new EnumMap(EntityGeneratorType.class);
        entityGenerators.forEach(entityGenerator -> entityGeneratorEnumMap.put(entityGenerator.type(), entityGenerator));
    }

    public EntityGenerator get(EntityGeneratorType type) {
        return entityGeneratorEnumMap.get(type);
    }
}
