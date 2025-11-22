package com.centrodeportivo.reservas.controller;

import com.centrodeportivo.reservas.model.Employee;
import com.centrodeportivo.reservas.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return employeeService.listar(page, size);
    }

    @GetMapping("/{id}")
    public Employee obtener(@PathVariable Long id) {
        return employeeService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee crear(@RequestBody Employee e) {
        return employeeService.crear(e);
    }

    @PutMapping("/{id}")
    public Employee actualizar(@PathVariable Long id, @RequestBody Employee e) {
        return employeeService.actualizar(id, e);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        employeeService.eliminar(id);
    }
}
