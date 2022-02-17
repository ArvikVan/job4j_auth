package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Employee;

/**
 * @author ArvikV
 * @version 1.0
 * @since 17.02.2022
 */
public interface EmployeesRepository extends CrudRepository<Employee, Integer> {
}
