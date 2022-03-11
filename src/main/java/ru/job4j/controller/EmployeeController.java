package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.repository.EmployeesRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author ArvikV
 * @version 1.0
 * @since 17.02.2022
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeesRepository employeesRepository;
    @Autowired
    private RestTemplate rest;

    private static final String API = "http://localhost:8083/person/";
    private static final String API_ID = "http://localhost:8083/person/{id}";

    public EmployeeController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    /**
     * метод для отображение всех сотрудников
     * @return на выходе список сотрудников
     */
    @GetMapping("/")
    public List<Employee> findAll() {
        return StreamSupport.stream(
                this.employeesRepository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    /**
     * созадем сотрудника
     * @param employee сотрудник
     * @return на выходе создан
     */
    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                this.employeesRepository.save(employee),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateEmp(@RequestBody Employee employee) {
        rest.put(API, employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }

}
