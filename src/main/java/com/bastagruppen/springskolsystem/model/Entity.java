package com.bastagruppen.springskolsystem.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Getter
@SuperBuilder
public abstract class Entity {

    protected final UUID id;

    @SuppressWarnings("unused")
    protected Entity() {
        this.id = randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}