package basic.example2;

//import basic.example2.RootController2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//UI: Root.fxml(기본), AddForm.fxml(추가), BarChart.fxml(차트)
//Control: RootController2.java
public class AppMain2 extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Root2.fxml"));
		BorderPane root = loader.load();
		
		RootController2 controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("AppMain2");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
