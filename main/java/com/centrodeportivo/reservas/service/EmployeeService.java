package com.centrodeportivo.reservas.service;

import com.centrodeportivo.reservas.exception.NotFoundException;
import com.centrodeportivo.reservas.model.Employee;
import com.centrodeportivo.reservas.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> listar(int page, int size) {
        List<Employee> todos = employeeRepository.findAll().stream()
                .sorted(Comparator.comparing(Employee::getId))
                .toList();
        int from = Math.min(page * size, todos.size());
        int to = Math.min(from + size, todos.size());
        return todos.subList(from, to);
    }

    public Employee obtener(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
    }

    public Employee crear(Employee e) {
        return employeeRepository.save(e);
    }

    public Employee actualizar(Long id, Employee e) {
        e.setId(id);
        return employeeRepository.save(e);
    }

    public void eliminar(Long id) {
        employeeRepository.deleteById(id);
    }
}
