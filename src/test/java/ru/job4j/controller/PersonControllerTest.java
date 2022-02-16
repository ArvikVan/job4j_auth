package ru.job4j.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.Main;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author ArvikV
 * @version 1.0
 * @since 16.02.2022
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository repository;

    @Test
    void whenFindAll() throws Exception {
        String result = "[{\"id\":1,\"login\":\"parsentev\",\"password\":\"123\"},"
                + "{\"id\":2,\"login\":\"ban\",\"password\":\"123\"},"
                + "{\"id\":3,\"login\":\"ivan\",\"password\":\"123\"}]";
        mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(result));
    }


    @Test
    public void whenFindById() throws Exception {
        String result = "{\"id\":1,\"login\":\"parsentev\",\"password\":\"123\"}";
        mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(result));

    }

    @Test
    public void whenCreate() throws Exception {
        Gson gson = new Gson();
        Person person = Person.of(4, "NEWPERS", "123");
        String forAdd = gson.toJson(person);
        String result = "[{\"id\":1,\"login\":\"parsentev\",\"password\":\"123\"},"
                + "{\"id\":2,\"login\":\"ban\",\"password\":\"123\"},"
                + "{\"id\":3,\"login\":\"ivan\",\"password\":\"123\"},"
                + "{\"id\":4,\"login\":\"NEWPERS\",\"password\":\"123\"}]";
        mockMvc.perform(post("/person/").contentType("application/json").content(forAdd))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"));
        mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(result));
    }

    @Test
    public void whenDelete() throws Exception {
        String result = "[{\"id\":1,\"login\":\"parsentev\",\"password\":\"123\"},"
                + "{\"id\":3,\"login\":\"ivan\",\"password\":\"123\"},"
                + "{\"id\":4,\"login\":\"NEWPERS\",\"password\":\"123\"}]";
        mockMvc.perform(delete("/person/2"))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(result));
    }


    @Test
    public void whenUpdate() throws Exception {
        Gson gson = new Gson();
        Person person = Person.of(4, "UpdatedLogin", "123");
        String forUpdate = gson.toJson(person);
        String result = "[{\"id\":1,\"login\":\"parsentev\",\"password\":\"123\"},"
                + "{\"id\":3,\"login\":\"ivan\",\"password\":\"123\"},"
                + "{\"id\":4,\"login\":\"UpdatedLogin\",\"password\":\"123\"}]";
        mockMvc.perform(put("/person/").contentType("application/json").content(forUpdate))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(result));
    }
}