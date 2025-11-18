package com.sergey;

// ИЗМЕНЕНИЕ: Добавлены новые импорты для "строителя"
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets; // Добавлен для надежности
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Парсер для чтения данных о людях из CSV-файла.
 * @author Белявцев Сергей
 * @version 1.0
 */
public class CsvParser {
    /** Разделитель, используемый в CSV-файле. */
    private static final char SEPARATOR = ';';
    /** Форматтер для преобразования строки с датой в объект LocalDate. */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Считывает данные из CSV-файла и преобразует их в список объектов Person.
     * @param csvFilePath Путь к файлу в папке resources.
     * @return Список объектов Person.
     * @throws IOException если возникает ошибка при чтении файла.
     * @throws CsvException если возникает ошибка при парсинге CSV.
     */
    public List<Person> parse(String csvFilePath) throws IOException, CsvException {
        List<Person> people = new ArrayList<>();
        // Эта карта будет хранить уже созданные подразделения, чтобы не создавать дубликаты.
        // Ключ - название подразделения, Значение - объект Division.
        Map<String, Division> divisionCache = new HashMap<>();
        int nextDivisionId = 1; // Счетчик для генерации уникальных ID для новых подразделений.

        InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
        if (in == null) {
            throw new IOException("Файл не найден в ресурсах: " + csvFilePath);
        }

        // ИСПРАВЛЕНИЕ: Используем "строитель" CSVReaderBuilder, чтобы правильно указать разделитель.
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in, StandardCharsets.UTF_8))
                .withCSVParser(new CSVParserBuilder().withSeparator(SEPARATOR).build())
                .build()) {

            // Пропускаем заголовок
            reader.skip(1);

            // Читаем все строки из файла в список. Каждая строка - это массив String[].
            List<String[]> allRows = reader.readAll();

            // Проходим по каждой строке
            for (String[] row : allRows) {
                // Преобразуем каждую ячейку в нужный тип данных
                int id = Integer.parseInt(row[0]);
                String name = row[1];
                Gender gender = row[2].equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE;
                LocalDate birthDate = LocalDate.parse(row[3], DATE_FORMATTER);

                String divisionName = row[4];

                // Проверяем, создавали ли мы уже такое подразделение
                Division division = divisionCache.get(divisionName);
                if (division == null) {
                    // Если нет, создаем новое, генерируем ID и кладем в "кэш"
                    division = new Division(nextDivisionId++, divisionName);
                    divisionCache.put(divisionName, division);
                }

                int salary = Integer.parseInt(row[5]);

                // Создаем объект Person и добавляем его в наш итоговый список
                people.add(new Person(id, name, gender, birthDate, division, salary));
            }
        }
        return people;
    }
}