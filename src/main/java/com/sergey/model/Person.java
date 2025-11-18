package com.sergey.model;

import java.time.LocalDate;

/**
 * Класс, представляющий человека (сотрудника)
 * Является неизменяемым объектом-контейнером для данных
 *
 * @author Белявцев Сергей
 * @version 1.0
 */
public class Person {
    private final int id;
    private final String name;
    private final Gender gender;
    private final LocalDate birthDate;
    private final Division division;
    private final int salary;
    /**
     * Создает новый объект человека
     *
     * @param id        Уникальный идентификатор
     * @param name      Имя
     * @param gender    Пол (из перечисления Gender)
     * @param birthDate Дата рождения
     * @param division  Объект подразделения
     * @param salary    Зарплата
     */
    public Person(int id, String name, Gender gender, LocalDate birthDate, Division division, int salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.division = division;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Gender getGender() { return gender; }
    public LocalDate getBirthDate() { return birthDate; }
    public Division getDivision() { return division; }
    public int getSalary() { return salary; }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", division=" + division +
                ", salary=" + salary +
                '}';
    }
}