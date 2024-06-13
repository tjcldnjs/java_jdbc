package ch04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TransactionExample2 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/m_board?serverTimezone=Asia/Seoul";
		String id = "root";
		String password = "asd123";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, id, password)) {

				conn.setAutoCommit(true);
				String sqlInsert = " insert into reply (userId, boardId, content, createDate) values (?,?,?,now()); ";

				PreparedStatement psmt1 = conn.prepareStatement(sqlInsert);
				psmt1.setInt(1, 3);
				psmt1.setInt(2, 1);
				psmt1.setString(3, "안녕하세요");
				psmt1.executeUpdate();

				String sqlUpdate = " Update reply set content = ? where userId = ? ";
				PreparedStatement psmt2 = conn.prepareStatement(sqlUpdate);
				psmt2.setString(1, "반가워요");
				psmt2.setInt(2, 2);
				psmt2.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	} // end of main
}
