package dao;

import model.City;

import java.util.List;

public interface CityDao {

    // Добавление объекта
    void create(City city);

    // Получение объекта по id
    City readById(int id);

    // Получение всех объектов
    List<City> readAll();

    // Изменение объекта по id
    void updateEmployee(City city);

    // Удаление объекта по id
    void deleteEmployee(City city);
}
