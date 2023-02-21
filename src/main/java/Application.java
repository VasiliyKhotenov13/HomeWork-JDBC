import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.Employee;

import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {

        // Создаём объект класса DAO
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        Employee empl1 = new Employee(11, "Fedor", "Emelianenko", "male", 45, 6);
        // Создаём новый объект
        employeeDao.create(empl1);

        // Получаем объект по id
        System.out.println(employeeDao.readById(11));

        // Получаем полный список объектов
        List<Employee> list = employeeDao.readAll();
        for (Employee employee : list) {
            System.out.println(employee);
        }

        Employee empl2 = new Employee(35,"Grisha", "Budkin", "male", 33, 2);

        // Изменяем объект
        employeeDao.updateEmployee(empl2);

        // Удаляем объект
        employeeDao.deleteEmployee(empl2);
    }
}

