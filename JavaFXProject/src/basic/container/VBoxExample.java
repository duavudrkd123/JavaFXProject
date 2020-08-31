package basic.container;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample  extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();//컨테이너 생성
		root.setPadding(new Insets(10, 10, 10, 10));
		
		ImageView iv = new ImageView();
		iv.setFitWidth(200);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/basic/image/sample.jpg"));
		//D:\Dev\git\JavaFXProject\JavaFXProject\JavaFXProject\bin에서부터 시작
		
		HBox hbox = new HBox(); //컨테이너 생성
		hbox.setAlignment(Pos.CENTER); //가운데 정렬 
		hbox.setSpacing(20);
		
		Button btnPrev = new Button();
		btnPrev.setText("이전");
		Button btnNext = new Button("다음"); //생성자로 텍스트 바로넣기
		HBox.setHgrow(btnNext, Priority.ALWAYS);
		btnNext.setMaxWidth(Double.MAX_VALUE);
		hbox.getChildren().add(btnPrev);
		hbox.getChildren().add(btnNext);
		VBox.setMargin(hbox, new Insets(10));
		
		root.getChildren().add(iv);
		root.getChildren().add(hbox);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);//스테이지(신(컨테이너)))
		primaryStage.show();
		primaryStage.setTitle("VBox 예제");
		primaryStage.setResizable(false);//크기 조정 불가능하게 만듦
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
