import dao.CityDao;
import dao.CityDaoImpl;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {

        // Создаём объект класса DAO
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        CityDao cityDao = new CityDaoImpl();

        City city1 = new City("Kishinev");
        cityDao.create(city1);

        Employee empl1 = new Employee("Oleg", "Markov", "Male", 39);
        City city2 = cityDao.readById(7);
        empl1.setCityId(city2);
        employeeDao.create(empl1);

        City city3 = cityDao.readById(7);
        cityDao.deleteEmployee(city3);

        System.out.println(cityDao.readAll());
    }
}

