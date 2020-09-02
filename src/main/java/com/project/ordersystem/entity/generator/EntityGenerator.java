package com.project.ordersystem.entity.generator;

public abstract class EntityGenerator<T> {
   public abstract T generate(EntityGeneratorModel model);
}
