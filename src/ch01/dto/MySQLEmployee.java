package ch01.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLEmployee {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";

		// 필요 데이터 타입
		// JDBC API 레벨(자바 개발자들이 개념화 시켜 놓은 클래스들이다.)
		Connection connection = null; // 연결해주는 역할
		Statement statement = null; // 문자열 기반으로 쿼리를 실행
		ResultSet resultSet = null; // 받은 데이터를 결과로 보여줌

		// 1. MySQL 구현체를 사용하겠다는 설정을 해야합니다.
		// JDBC 드라이버 로드 (MySQL 구현 클래스를 로드)
		try {
			// 1. 메모리에 사용하는 드라이버(JDBC API를 구현한 클래스) 클래스를 띄운다.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 데이터 베이스 연결 설정
			connection = DriverManager.getConnection(url, user, password);

			// 3. SQL 실행
			statement = connection.createStatement();
			// 딱 2가지는 기억하자. 쿼리를 실행시키는 메소드

			statement.executeUpdate("insert into employee values(6,'서치원','ㅇㅇ부',300000,'2020-12-30')"); // -> Insert, Update, delete 사용
			resultSet = statement.executeQuery("SELECT * FROM employee"); // select 실행 시 사용한다.

			ArrayList<Employee> List = new ArrayList<>();

			while (resultSet.next()) {
				Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("department"), resultSet.getDouble("salary"),
						resultSet.getString("hire_date"));
				List.add(employee);
			}

			for (Employee employee : List) {
				System.out.println(employee.getName());
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
