package com.sergey;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;

/**
 * Главный класс для запуска приложения по парсингу CSV
 * @author Белявцев Сергей
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        CsvParser parser = new CsvParser();
        try {
            // Указываем путь к нашему файлу в папке resources
            List<Person> people = parser.parse("foreign_names.csv");

            System.out.println("Успешно считано " + people.size() + " человек.");

            // Выведем первых 10 записей для примера
            System.out.println("--- Первые 10 записей ---");
            for (int i = 0; i < 10 && i < people.size(); i++) {
                System.out.println(people.get(i));
            }

        } catch (IOException | CsvException e) {
            // Если произойдет ошибка, мы ее здесь поймаем и выведем сообщение
            System.err.println("Произошла ошибка при чтении или парсинге файла:");
            e.printStackTrace();
        }
    }
}