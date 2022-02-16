package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Person;

/**
 * @author ArvikV
 * @version 1.0
 * @since 16.02.2022
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
