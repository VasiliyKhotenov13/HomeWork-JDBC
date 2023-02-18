package dao;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(Queries.INSERT.query)) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Невозможно создать нового сотрудника.");
        }

    }

    @Override
    public Employee readById(int id) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(Queries.READ.query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");
                return new Employee(id, firstName, lastName, gender, age, cityId);
            }
        } catch (SQLException e) {
            throw new SQLException("Невозможно найти объект по запросу.");
        }
        return  null;
    }

    @Override
    public List<Employee> readAll() throws SQLException {

        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(Queries.GET_ALL.query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");

                employees.add(new Employee(id, firstName, lastName, gender, age, cityId));
            }
        } catch (SQLException e) {
            throw new SQLException("Невозможно вывести весь список сотрудников.");
        }
        return employees;
    }

    @Override
    public void updateEmployeeById(int id, String firstName, String lastName,
                                   String gender, int age, int cityId) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(Queries.UPDATE.query)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, cityId);
            statement.setInt(6, id);

            statement.executeQuery();
        } catch (SQLException e) {
            throw new SQLException("Невозможно найти объект по запросу.");
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(Queries.DELETE.query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Невозможно удалить сотрудника.");
        }
    }

    enum Queries {

        GET_ALL("SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id"),
        INSERT("INSERT INTO employee(first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))"),
        DELETE("DELETE FROM employee WHERE id=(?)"),
        UPDATE("UPDATE employee SET first_name=(?), last_name=(?), gender=(?), age=(?), city_id=(?) WHERE id=(?)"),
        READ("SELECT * FROM employee WHERE id = ?");

        final String query;

        Queries(String query) {
            this.query = query;
        }
    }
}
