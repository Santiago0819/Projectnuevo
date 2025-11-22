package com.centrodeportivo.reservas.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public abstract class CsvRepository<T extends CsvEntity> {

    private final Path filePath;
    private final ReentrantLock lock = new ReentrantLock();
    protected Map<Long, T> data = new HashMap<>();
    private long currentId = 0L;

    protected CsvRepository(String fileName) {
        this.filePath = Path.of("data", fileName);
        cargarDesdeCsv();
    }

    protected abstract String getHeaders();
    protected abstract String toCsvRow(T entity);
    protected abstract T fromCsvRow(String row);

    private void cargarDesdeCsv() {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                    writer.write(getHeaders());
                    writer.newLine();
                }
                return;
            }

            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String header = reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.isBlank()) continue;
                    T entity = fromCsvRow(line);
                    data.put(entity.getId(), entity);
                    currentId = Math.max(currentId, entity.getId());
                }
            }
        } catch (IOException e) {
            log.error("Error cargando CSV {}: {}", filePath, e.getMessage());
        }
    }

    private void guardarEnCsv() {
        lock.lock();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(getHeaders());
            writer.newLine();
            for (T entity : data.values()) {
                writer.write(toCsvRow(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("Error guardando CSV {}: {}", filePath, e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(++currentId);
        }
        data.put(entity.getId(), entity);
        guardarEnCsv();
        return entity;
    }

    public void deleteById(Long id) {
        data.remove(id);
        guardarEnCsv();
    }
}
