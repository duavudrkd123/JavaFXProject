//package basic.example2;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import basic.common.ConnectionDB;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//public class StudentDAO {
//	static Connection conn = ConnectionDB.getDB();
//	ObservableList<Student2> list;
//	
//	public static void handleBtnClearAction() {
//		String sql = "delete from STUDENT";
//		try {
//			PreparedStatement pstmt =conn.prepareStatement(sql);
//			pstmt.executeQuery();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		RootController2.listAdd();	
//	}
//
//	
//}
