package com.sergey;

import java.util.Objects;

/**
 * Класс, представляющий подразделение
 * @author Белявцев Сергей
 * @version 1.0
 */
public class Division {
    private final int id;
    private final String name;

    /**
     * Создает новый объект подразделения
     *
     * @param id   Уникальный идентификатор подразделения
     * @param name Название подразделения
     */
    public Division(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Возвращает название подразделения
     * @return Название подразделения
     */
    public String getName() {
        return name;
    }
    /**
     * Возвращает строковое представление объекта, которым является его имя.
     * @return Название подразделения.
     */
    @Override
    public String toString() {
        return name; // Это нужно, чтобы при печати объекта Person выводилось просто имя подразделения
    }

    // Эти два метода (equals и hashCode) очень важны
    // Они позволяют Java правильно сравнивать объекты и работать с ними в коллекциях
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return name.equals(division.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}