package basic.example2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController2 implements Initializable {
	@FXML TableView<Student2> tableView;
	@FXML Button btnAdd, btnBarChart, btnClear;
	
	ObservableList<Student2> list;
	Connection conn = ConnectionDB2.getDB();
	
	Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		TableColumn<Student2, ?> tc = tableView.getColumns().get(0);//첫번째칼럼
		tc.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));
		
		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));
		
		tc = tableView.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		
		//데이터불러오기
		listAdd();
		
		//추가버튼
		btnAdd.setOnAction(e -> handleBtnAddAction());
		
		//차트버튼
		btnBarChart.setOnAction(e -> handleBtnChartAction());
		
		//데이터 삭제
		btnClear.setOnAction(e -> handleBtnClearAction());
		
		//수정
		tableView.setOnMouseClicked(event -> {
				if(event.getClickCount() == 2) { //더블클릭이면
					String selectedId = tableView.getSelectionModel().getSelectedItem().getId();
					handleDoubleClickAction(selectedId);
				}
		});
		
	}//end of initialize
	
	public void handleBtnClearAction() {
		String sql = "delete from STUDENT";
		try {
			PreparedStatement pstmt =conn.prepareStatement(sql);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		listAdd();
	}
	
	//DB에서 자료 불러오기 메서드
	public void listAdd() {
		String sql = "select * from STUDENT";
			try {
			 	list = FXCollections.observableArrayList();   
			 	PreparedStatement pstmt =conn.prepareStatement(sql);
	            ResultSet rs = pstmt.executeQuery();
	            while(rs.next()) {
	               Student2 stu = new Student2(rs.getString("ID"), rs.getString("NAME"),
	                     rs.getInt("KOREAN"), rs.getInt("MATH"), rs.getInt("ENGLISH"));
	               list.add(stu);
	            };
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			tableView.setItems(list);
	}
	
	//추가화면(AddForm) 보여주는 작업
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(btnAdd.getScene().getWindow());
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm2.fxml"));
			
			Scene scene = new Scene(parent);
			stage.setTitle("AddForm2");
			stage.setScene(scene);
			stage.show();
			
			//추가화면의 컨트롤 사용하기
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					TextField txtID = (TextField) parent.lookup("#txtID");
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
					
//					Student2 student = new Student2(
//						txtID.getText(),
//						txtName.getText(), 
//						Integer.parseInt(txtKorean.getText()), 
//						Integer.parseInt(txtMath.getText()), 
//						Integer.parseInt(txtEnglish.getText()));
//					
//					list.add(student);
									
					String sql = "insert into STUDENT values(?,?,?,?,?)";
					try {
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setNString(1,	txtID.getText());
						pstmt.setNString(2,	txtName.getText());
						pstmt.setNString(3,	txtKorean.getText());
						pstmt.setNString(4, txtMath.getText());
						pstmt.setNString(5, txtEnglish.getText());
						pstmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					stage.close();
					listAdd();
				}
			});
			
			//추가화면의 취소버튼
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(e -> {
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
				
				txtName.clear();
				txtKorean.clear();
				txtMath.clear();
				txtEnglish.clear();
				
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void handleDoubleClickAction(String ID) {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210, 230);
		
		Label lID, lName, lKorean, lMath, lEnglish, tID;
		TextField  tKorean, tMath, tEnglish, tName;
		int startY = 20;
		lID = new Label("ID");
		lID.setLayoutX(35);
		lID.setLayoutY(startY);
		
		lName = new Label("이름");
		lName.setLayoutX(35);
		lName.setLayoutY(startY + 30);
		
		lKorean = new Label("국어");
		lKorean.setLayoutX(35);
		lKorean.setLayoutY(startY + 60);
		
		lMath = new Label("수학");
		lMath.setLayoutX(35);
		lMath.setLayoutY(startY + 90);
		
		lEnglish = new Label("영어");
		lEnglish.setLayoutX(35);
		lEnglish.setLayoutY(startY + 120);
		
		tID = new Label(ID);
		tID.setPrefWidth(110);
		tID.setLayoutX(72);
		tID.setLayoutY(startY);
		
		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(startY+30);
		
		tKorean = new TextField();
		tKorean.setPrefWidth(110);
		tKorean.setLayoutX(72);
		tKorean.setLayoutY(startY+60);
		
		tMath = new TextField();
		tMath.setPrefWidth(110);
		tMath.setLayoutX(72);
		tMath.setLayoutY(startY+90);
		
		tEnglish = new TextField();
		tEnglish.setPrefWidth(110);
		tEnglish.setLayoutX(72);
		tEnglish.setLayoutY(startY+120);
		
		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(60);
		btnUpdate.setLayoutY(184);
		btnUpdate.setOnAction(event -> {
	
				String sql = "UPDATE STUDENT SET name = ?, KOREAN = ?, math = ?, english = ? WHERE ID = ?";
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setNString(1,	tName.getText());
					pstmt.setNString(2,	tKorean.getText());
					pstmt.setNString(3,	tMath.getText());
					pstmt.setNString(4, tEnglish.getText());
					pstmt.setNString(5, tID.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stage.close();
				listAdd();
		});
		
		Button btnDelte = new Button("삭제");
		btnDelte.setLayoutX(120);
		btnDelte.setLayoutY(184);
		btnDelte.setOnAction(event -> {
				
				String sql = "DELETE from STUDENT WHERE ID = ?";
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setNString(1, tID.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stage.close();
				listAdd();
		});
		
		
		//이름기준으로 국어, 수학, 영어 점수를 화면에 입력해주기
		for(Student2 stu : list) {
			if(stu.getId().equals(ID)) {
				tID.setText(String.valueOf(stu.getId()));
				tName.setText(String.valueOf(stu.getName()));
				tMath.setText(String.valueOf(stu.getMath()));
				tKorean.setText(String.valueOf(stu.getKorean()));
				tEnglish.setText(String.valueOf(stu.getEnglish()));
			}
		}
		
		ap.getChildren().addAll(tID, tName, tKorean, tMath, tEnglish, lID, lName, lKorean, lMath, lEnglish, btnUpdate, btnDelte);
		
		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void handleBtnChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		try {
			Parent chart = FXMLLoader.load(getClass().getResource("BarChart2.fxml"));
			Scene scene = new Scene(chart);
			stage.setScene(scene);
			stage.setTitle("BarChart2");
			stage.show();
			
			//chart 가지고와서 series 추가하기
			BarChart<String, Integer> barChart = (BarChart<String, Integer>)chart.lookup("#barChart");
			
			//국어 category
			XYChart.Series<String, Integer> seriesK = new XYChart.Series<String, Integer>();
			seriesK.setName("국어");
			
			ObservableList<XYChart.Data<String, Integer>> koreanList = FXCollections.observableArrayList();
			
			for(int i=0; i<list.size(); i++) {
				koreanList.add(new XYChart.Data<>(list.get(i).getName(), 
												  list.get(i).getKorean()));
			}
			
			seriesK.setData(koreanList);
			barChart.getData().add(seriesK);
			
			//수학 category
			XYChart.Series<String, Integer> seriesM = new XYChart.Series<String, Integer>();
			seriesM.setName("수학");
			
			ObservableList<XYChart.Data<String, Integer>> mathList = FXCollections.observableArrayList();
			
			for(int i=0; i<list.size(); i++) {
				mathList.add(new XYChart.Data<>(list.get(i).getName(), 
												list.get(i).getMath()));
			}
			
			seriesM.setData(mathList);
			barChart.getData().add(seriesM);
			
			//영어 category
			XYChart.Series<String, Integer> seriesE = new XYChart.Series<String, Integer>();
			seriesE.setName("영어");
			
			ObservableList<XYChart.Data<String, Integer>> englishList = FXCollections.observableArrayList();
			
			for(int i=0; i<list.size(); i++) {
				englishList.add(new XYChart.Data<>(list.get(i).getName(), 
												   list.get(i).getEnglish()));
			} 
			
			seriesE.setData(englishList);
			barChart.getData().add(seriesE);
			
			Button btnClose = (Button) chart.lookup("#btnClose");
			btnClose.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					stage.close();
				}
		
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
