package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertExample {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 루트 계정사용 금지
		String password = "asd123";

		// Connection 객체를 얻어서 insert 구문을 직접 만들어 보세요.
		// mydb2 사용, employees 테이블에서 값을 넣는 코드를 작성하세요

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, user, password);

			String query = "insert into employee values(?,?,?,?, now())";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 8);
			preparedStatement.setString(2, "서치원");
			preparedStatement.setString(3, "IT");
			preparedStatement.setString(4, "5000000.00");

			int rowCount = preparedStatement.executeUpdate();
			System.out.println(rowCount);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // end of main

}
