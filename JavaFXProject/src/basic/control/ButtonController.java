package basic.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ButtonController implements Initializable{
	@FXML CheckBox chk1, chk2;
	@FXML ToggleGroup group;
	@FXML ImageView radioImageView, checkImageView;
	@FXML Button btnExit;
	@FXML RadioButton rad1, rad2, rad3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
//				System.out.println(observable.getValue().toString());
//				System.out.println("oldValue: " + oldValue.getUserData());
//				System.out.println("newValue: " + newValue.getUserData());
				radioImageView.setImage(new Image("basic/images/" + newValue.getUserData().toString() + ".png"));
			}
			
		});
		
		rad1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				System.out.println("BubbleChart가 선택되었습니다.");
			}
		});
		rad2.setOnMouseClicked((a) -> {System.out.println("BarChart가 선택되었습니다.");});
		rad3.setOnMouseClicked((a) -> {System.out.println("AreaChart가 선택되었습니다.");});
		
//		chk1.setOnAction((arg0) -> handleChkAction());
//		chk2.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				handleChkAction();
//			}
//			
//		});
		
//		btnExit.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				Platform.exit();
//			}
//		});
		btnExit.setOnAction((event) -> Platform.exit());
		
	}//end of intialize
	
	public void handleChkAction(){
		String imgName = "";
		if(chk1.isSelected() && chk2.isSelected()) {
			imgName = "geek-glasses-hair.gif";
		} else if (chk1.isSelected()) {
			imgName = "geek-glasses.gif";
		} else if(chk2.isSelected()) {
			imgName = "geek-hair.gif";
		} else {
			imgName = "geek.gif";
		}
		checkImageView.setImage(new Image("/basic/images/" + imgName));
	}
	
}//end of class
