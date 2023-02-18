import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "woodoo1992";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password)) {

            EmployeeDao employeeDao = new EmployeeDaoImpl(connection);

            // Добавление объекта
//            Employee pupkin = new Employee(8, "Viktor", "Arbuzov", "male", 25, 2);
//            employeeDao.create(pupkin);
//
//            // Получение объекта по id
            System.out.println(employeeDao.readById(3));
//
            // Изменение объекта по id
//            employeeDao.updateEmployeeById(5, "Dmitriy", "Petrov", "male", 33, 7);

            // Удаление объекта по id
//            employeeDao.deleteById(12);

            // Получение всех объектов
            List<Employee> employees = employeeDao.readAll();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}

