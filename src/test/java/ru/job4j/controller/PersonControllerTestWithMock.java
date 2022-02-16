package ru.job4j.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 16.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class PersonControllerTestWithMock {
    @Mock
    private PersonRepository personRepository;
    @Autowired
    @InjectMocks
    private PersonController personController;
    private Person person1;
    private Person person2;
    List<Person> personList;

    @BeforeEach
    public void setUp() {
        personList = new ArrayList<>();
        person1 = Person.of(1, "Login1", "Pass1");
        person2 = Person.of(2, "Login2", "Pass2");
        personList.add(person1);
        personList.add(person2);
    }

    @AfterEach
    public void tearDown() {
        person1 = person2 = null;
        personList = null;
    }

    @Test
    public void createPerson() {
        when(personRepository.save(any())).thenReturn(person1);
        personController.create(person1);
        verify(personRepository, times(1)).save(any());
    }

    @Test
    public void retrieveAllPersons() {
        personRepository.save(person1);
        when(personRepository.findAll()).thenReturn(personList);
        List<Person> personList2 = personController.findAll();
        assertEquals(personList2, personList);
        verify(personRepository, times(1)).save(person1);
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void retrievePersonById() {
        Mockito.when(personRepository.findById(1)).thenReturn(Optional.ofNullable(person1));
        Assertions.assertEquals(personRepository.findById(person1.getId()), Optional.ofNullable(person1));
    }

}
