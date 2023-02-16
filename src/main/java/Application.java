import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        // Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        final String password = "woodoo1992";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        // Создаем соединение с базой с помощью Connection
        // Формируем запрос к базе с помощью PreparedStatement
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, 1);

            // Делаем запрос к базе и результат кладем в ResultSet
            final ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while (resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                String firstNameInEmployee = "First name: " + resultSet.getString("first_name");
                String lastNameInEmployee = "Last name: " + resultSet.getString("last_name");
                String genderInEmployee = "Gender: " + resultSet.getString("gender");
                int ageInEmployee = resultSet.getInt("age");
                int cityIdInEmployee = resultSet.getInt(6);

                // Выводим данные в консоль
                System.out.println(firstNameInEmployee);
                System.out.println(lastNameInEmployee);
                System.out.println(genderInEmployee);
                System.out.println("Age: " + ageInEmployee);
                System.out.println("City id: " + cityIdInEmployee);

            }
        }
    }
}
