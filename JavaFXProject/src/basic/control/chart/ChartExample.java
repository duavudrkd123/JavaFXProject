package basic.control.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//UI: Chart.fxml
//Control: ChartController.java
public class ChartExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("ChartExample");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
