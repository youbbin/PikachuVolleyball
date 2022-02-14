package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import frame.IntroFrame;
import play.Play;
import play.Pokeball;
import frame.IntroFrame;

public class Database {
	Connection conn;
	String id;
	String pw;
	String url;
	PreparedStatement pstmt;

	public Database() {
		id = "root";
		pw = "5478";
		url = "jdbc:mysql://localhost:3306/mydatabase?serverTimezone=UTC";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void UpdateTable() {
		String sql = "INSERT INTO pikachuvolleyball "
				+ "(date,player1_name,player1_score,player2_name,player2_score,winner) "
				+ "VALUES (?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(LocalDate.now()));
			pstmt.setString(2, IntroFrame.nick1);
			pstmt.setInt(3, Pokeball.p1Score);
			pstmt.setString(4, IntroFrame.nick2);
			pstmt.setInt(5, Pokeball.p2Score);
			pstmt.setString(6, Play.winner);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getWinner() {
		String sql = "select winner from pikachuvolleyball where date = ?";
		pstmt = null;
		String winner = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(LocalDate.now())); // 오늘 날짜인 데이터만 가져오기
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				winner += rs.getString("winner") + " ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return winner;
	}
}
