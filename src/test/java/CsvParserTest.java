package com.sergey;

import com.opencsv.exceptions.CsvException;
import com.sergey.model.Gender;
import com.sergey.model.Person;
import com.sergey.parser.CsvParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса CsvParser.
 * @author Белявцев Сергей
 * @version 1.0
 */
class CsvParserTest {

    private CsvParser parser;

    @BeforeEach
    void setUp() {
        // Создаем новый экземпляр парсера перед каждым тестом
        parser = new CsvParser();
    }

    /**
     * Проверяет успешный парсинг корректного CSV-файла.
     */
    @Test
    void testParseSuccessful() throws IOException, CsvException {
        // Act (Действие): Запускаем парсер на нашем тестовом файле
        List<Person> people = parser.parse("test_data.csv");

        // Assert (Проверки)
        assertNotNull(people, "Список не должен быть null");
        assertEquals(3, people.size(), "Должно быть прочитано 3 человека");

        // Проверяем первого человека в списке
        Person person1 = people.get(0);
        assertEquals(1, person1.getId());
        assertEquals("John Doe", person1.getName());
        assertEquals(Gender.MALE, person1.getGender());
        assertEquals(LocalDate.of(1990, 1, 1), person1.getBirthDate());
        assertEquals("A", person1.getDivision().getName());
        assertEquals(5000, person1.getSalary());

        // Проверяем второго человека
        Person person2 = people.get(1);
        assertEquals(2, person2.getId());
        assertEquals("Jane Smith", person2.getName());
        assertEquals(Gender.FEMALE, person2.getGender());

        // Проверяем третьего человека
        Person person3 = people.get(2);
        assertEquals("Peter Jones", person3.getName());
        assertEquals("A", person3.getDivision().getName());

        // САМАЯ ВАЖНАЯ ПРОВЕРКА: убеждаемся, что для двух людей из одного подразделения
        // используется ОДИН И ТОТ ЖЕ объект Division (проверяем работу кэша)
        assertSame(person1.getDivision(), person3.getDivision(),
                "Объекты Division для одного подразделения должны совпадать");
    }

    /**
     * Проверяет, что парсер выбрасывает исключение, если файл не найден
     */
    @Test
    void testFileNotFound() {
        // Проверяем, что вызов parser.parse() с несуществующим файлом
        // приведет к ошибке IOException
        assertThrows(IOException.class, () -> {
            parser.parse("non_existent_file.csv");
        });
    }
}