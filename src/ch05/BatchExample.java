package ch05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchExample {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/demo3?serverTimezone=Asia/Seoul"; // 문자열로 되어있는 경로가 필요함
		// 응용코드가 데이터베이스에 로그인해야하므로 아이디와 패스워드가 필요함
		String user = "root";
		String password = "asd123";
		Connection conn = null;
		try {
			// db접근기술 쓰려면 MySQL 드라이버 로드 써야함
			// Class 클래스
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 자바 표준 API
			conn = DriverManager.getConnection(url, user, password);
			// 트랜잭션 처리
			conn.setAutoCommit(false); // 수동 커밋으로 변경

			// 배치 처리 --> User 테이블에 한번에 사용자를 3명을 넣어보자.
			String sql = " Insert into users(name, email) values(?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql); // Statement 좀더 향상된 기법

			// 1번 사용자 처리
			pstmt.setString(1, "유저1");
			pstmt.setString(2, "유저1@nate.com");
			// pstmt.executeUpdate();
			// 배치 처리 하기위해 호출할 메서드가 필요
			pstmt.addBatch(); // <--

			// 2번 사용자 처리
			pstmt.setString(1, "유저2");
			pstmt.setString(2, "유저2@nate.com");
			pstmt.addBatch(); // <--

			// 3번 사용자 처리
			pstmt.setString(1, "유저3");
			pstmt.setString(2, "유저3@nate.com");
			pstmt.addBatch(); // <--

			int[] rowCounts = pstmt.executeBatch(); // 배열

			conn.commit(); // 물리적인 저장 장치에 영구히 반영하겠다.
			System.out.println("배치 처리 완료 : " + rowCounts.length);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	} // end of main

}
