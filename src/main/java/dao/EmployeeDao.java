package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    // Добавление объекта
    void create(Employee employee) throws SQLException;

    // Получение объекта по id
    Employee readById(int id) throws SQLException;

    // Получение всех объектов
    List<Employee> readAll() throws SQLException;

    // Изменение объекта по id
    void updateEmployeeById(int id, String firstName, String lastName, String gender, int age, int cityId) throws SQLException;

    // Удаление объекта по id
    void deleteById(int id) throws SQLException;

}
