package basic.control.chart;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;

public class ChartController implements Initializable {
	@FXML PieChart pieChart;
	@FXML BarChart barChart;
	@FXML AreaChart areaChart;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//pieChart
		ObservableList<Data> list = FXCollections.observableArrayList(
				new PieChart.Data("AWT", 10), new PieChart.Data("Swing", 30),
				new PieChart.Data("SWT", 25), new PieChart.Data("JavaFX", 35));
//		list.add(new PieChart.Data("AWT", 10));
//		list.add(new PieChart.Data("Swing", 30));
//		list.add(new PieChart.Data("SWT", 25));
//		list.add(new PieChart.Data("JavaFX", 35));
		pieChart.setData(list);
		
		//barChart
		XYChart.Series<String, Integer> s1 = new XYChart.Series<>();
		s1.setData(getSeries1());
		s1.setName("남자");
		XYChart.Series<String, Integer> s2 = new XYChart.Series<>();
		s2.setData(getSeries2());
		s2.setName("여자");
		
		barChart.getData().add(s1);
		barChart.getData().add(s2);
		
		//areaChart
		XYChart.Series<String, Integer> s3 = new XYChart.Series<>();
		s3.setData(getSeries3());
		s3.setName("온도");
		XYChart.Series<String, Integer> s4 = new XYChart.Series<>();
		s4.setData(getSeries4());
		s4.setName("코로나");
		
		areaChart.getData().add(s3);
		areaChart.getData().add(s4);
	}
	
	public ObservableList<XYChart.Data<String, Integer>> getSeries1() {
		ObservableList<XYChart.Data<String, Integer>> list =
				FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("2015", 70));
		list.add(new XYChart.Data<String, Integer>("2016", 40));
		list.add(new XYChart.Data<String, Integer>("2017", 50));
		list.add(new XYChart.Data<String, Integer>("2018", 30));
		return list;
	}
	
	public ObservableList<XYChart.Data<String, Integer>> getSeries2() {
		ObservableList<XYChart.Data<String, Integer>> list =
				FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("2015", 60));
		list.add(new XYChart.Data<String, Integer>("2016", 70));
		list.add(new XYChart.Data<String, Integer>("2017", 80));
		list.add(new XYChart.Data<String, Integer>("2018", 50));
		return list;
	}
	
	public ObservableList<XYChart.Data<String, Integer>> getSeries3() {
		Connection conn = ConnectionDB.getDB();
		String sql = "select * from receipt";
		ObservableList<XYChart.Data<String, Integer>> list 
								=FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new XYChart.Data<>(rs.getString("receipt_month"),
											rs.getInt("receipt_qty")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
//		ObservableList<XYChart.Data<String, Integer>> list =
//				FXCollections.observableArrayList();
//		list.add(new XYChart.Data<String, Integer>("09", 25));
//		list.add(new XYChart.Data<String, Integer>("10", 18));
//		list.add(new XYChart.Data<String, Integer>("11", 5));
//		list.add(new XYChart.Data<String, Integer>("12", 3));
//		return list;
	}
	
	public ObservableList<XYChart.Data<String, Integer>> getSeries4() {
		ObservableList<XYChart.Data<String, Integer>> list =
				FXCollections.observableArrayList();
		list.add(new XYChart.Data<String, Integer>("05", 500));
		list.add(new XYChart.Data<String, Integer>("06", 600));
		list.add(new XYChart.Data<String, Integer>("07", 650));
		list.add(new XYChart.Data<String, Integer>("08", 550));
		list.add(new XYChart.Data<String, Integer>("09", 625));
		list.add(new XYChart.Data<String, Integer>("10", 730));

		return list;
	}
}
