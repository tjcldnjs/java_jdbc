package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";

		Connection connection = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, user, password);

			String query = "Select * from employee";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("id"));
				System.out.println(resultSet.getString("name"));
				System.out.println(resultSet.getString("department"));
				System.out.println(resultSet.getString("salary"));
				System.out.println(resultSet.getString("hire_date"));
			}

			// int rowCount = preparedStatement.executeUpdate();
			// System.out.println(rowCount);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
