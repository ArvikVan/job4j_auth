package ru.job4j.domain;

import java.sql.Timestamp;

/**
 * @author ArvikV
 * @version 1.0
 * @since 17.02.2022
 */
public class Report {
    private int id;
    private String name;
    private Timestamp created;
    private Person person;

    public static Report of(int id, String name, Person person) {
        Report report = new Report();
        report.id = id;
        report.name = name;
        report.person = person;
        report.created = new Timestamp(System.currentTimeMillis());
        return report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
