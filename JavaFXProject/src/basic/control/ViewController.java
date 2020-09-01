//package basic.control;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TableView;
//import javafx.scene.image.ImageView;
//
//public class ViewController implements Initializable {
//	@FXML ListView<String> listView;
//	@FXML TableView<Phone> tableView;
//	@FXML ImageView imageView;
//	
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		listView.setItems(FXCollections.observableArrayList(
//			"갤럭시S1", "갤럭시S2", "갤럭시S3", "갤럭시S4", "갤럭시S5", "갤럭시S6", "갤럭시S7"
//		));
//		listView.getSelectionModel().selectedIndexProperty().addListener(
//				new ChangeListener<Number>() {
//
//					@Override
//					public void changed(ObservableValue<? extends Number> observable, Number oldValue,
//						Number newValue) {
//						tableView.get	
//						
//					}
//					
//				}
//				);}
//	
////	public void handleBtnOkAction(ActionEvent e) {
////		String item = listView.getSelectionModel().getSelectedItem();
////		System.out.println("ListView 스마트폰: " + item);
////		Phone phone
//	}
//}
