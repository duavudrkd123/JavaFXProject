package basic.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		TilePane root = new TilePane();
		root.setPrefTileHeight(100);
		root.setPrefTileWidth(100);
		
//		ImageView iv = new ImageView();
//		iv.setImage(new Image("/basic/images/fruit1.jpg"));
//		root.getChildren().add(iv);
//		ImageView iv2 = new ImageView();
//		iv2.setImage(new Image("/basic/images/fruit2.jpg"));
//		root.getChildren().add(iv2);
//		ImageView iv3 = new ImageView();
//		iv3.setImage(new Image("/basic/images/fruit3.jpg"));
//		root.getChildren().add(iv3);
//		ImageView iv4 = new ImageView();
//		iv4.setImage(new Image("/basic/images/fruit4.jpg"));
//		root.getChildren().add(iv4);
//		ImageView iv5 = new ImageView();
//		iv5.setImage(new Image("/basic/images/fruit5.jpg"));
//		root.getChildren().add(iv5);
		
		ImageView[] ary = new  ImageView[5];
		for(int i=0; i<5; i++) {
			ImageView iv = new ImageView();
			iv.setImage(new Image("/basic/images/fruit"+( i+1)+ ".jpg"));
			ary[i] = iv;
			root.getChildren().add(ary[i]);
		}
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("AppMain");
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
