package com.bastagruppen.springskolsystem.repository;

import com.bastagruppen.springskolsystem.model.Entity;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

public class InMemoryRepository<T extends Entity> {

    private final Map<UUID, T> records;

    public InMemoryRepository() {
        this.records = new ConcurrentHashMap<>();
    }

    public T save(T entity) {
        records.put(entity.getId(), entity);
        return entity;
    }

    public Collection<T> findAll() {
        return records.values();
    }

    public Optional<T> findById(UUID id) {
        return ofNullable(records.get(id));
    }

    public void delete(T entity) {
        if (isNull(entity))
            throw new NullPointerException("Entity is null");

        records.remove(entity.getId());
    }
}