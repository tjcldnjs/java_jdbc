package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";

		Connection connection = null;
		ResultSet resultset = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, user, password);

			String query = "delete from employee where id=?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 6);

			// resultset = preparedStatement.executeUpdate();

			int rowCount = preparedStatement.executeUpdate();
			System.out.println(rowCount);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
