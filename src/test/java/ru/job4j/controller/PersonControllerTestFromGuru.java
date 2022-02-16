package ru.job4j.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.Main;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ArvikV
 * @version 1.0
 * @since 16.02.2022
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonControllerTestFromGuru {
    @Autowired
    private PersonRepository personRepository;
    private Person person;

    @BeforeEach
    public void setup() {
        person = Person.of(1, "first", "123");
    }

    @AfterEach
    public void tearDown() {
        personRepository.deleteAll();
        person = null;
    }

    @Test
    public void createPerson() {
        personRepository.save(person);
        Person fetchingPerson = personRepository.findById(person.getId()).get();
        assertEquals(1, fetchingPerson.getId());
    }

    @Test
    public void retrieveAllPerson() {
        Person person1 = Person.of(1, "Login1", "Pass1");
        Person person2 = Person.of(2, "Login2", "Pass2");
        personRepository.save(person1);
        personRepository.save(person2);
        List<Person> personList = (List<Person>) personRepository.findAll();
        assertEquals("Login2", personList.get(1).getLogin());
    }

    @Test
    public void retrieveById() {
        Person person1 = Person.of(1, "Login1", "Pass1");
        Person person2 = Person.of(2, "Login2", "Pass2");
        personRepository.save(person1);
        personRepository.save(person2);
        Optional<Person> optionalPerson = personRepository.findById(person2.getId());
        assertEquals(person2.getId(), optionalPerson.get().getId());
        assertEquals(person2.getLogin(), optionalPerson.get().getLogin());
    }

    @Test
    public void deletePerson() {
        Person person1 = Person.of(1, "Login1", "Pass1");
        personRepository.save(person1);
        personRepository.deleteById(person1.getId());
        Optional optional = personRepository.findById(person1.getId());
        assertEquals(Optional.empty(), optional);
    }
}
