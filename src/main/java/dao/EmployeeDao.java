package dao;

import model.Employee;
import java.util.List;

public interface EmployeeDao {

    // Добавление объекта
    void create(Employee employee);

    // Получение объекта по id
    Employee readById(int id);

    // Получение всех объектов
    List<Employee> readAll();

    // Изменение объекта по id
    void updateEmployee(Employee employee);

    // Удаление объекта по id
    void deleteEmployee(Employee employee);
}
