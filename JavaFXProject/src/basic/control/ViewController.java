package basic.control;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ViewController implements Initializable {
	@FXML ListView<String> listView;
	@FXML TableView<Phone> tableView;
	@FXML ImageView imageView;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("갤럭시S1");
		list.add("갤럭시S2");
		list.add("갤럭시S3");
		list.add("갤럭시S4");
		list.add("갤럭시S5");
		list.add("갤럭시S6");
		list.add("갤럭시S7");
		listView.setItems(list);
		
		listView.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						tableView.getSelectionModel().select(newValue.intValue());
						tableView.scrollTo(newValue.intValue());
					}
		});
				
		ObservableList<Phone> phoneList = FXCollections.observableArrayList();
		phoneList.add(new Phone("갤럭시S1", "phone01.png"));
		phoneList.add(new Phone("갤럭시S2", "phone02.png"));
		phoneList.add(new Phone("갤럭시S3", "phone03.png"));
		phoneList.add(new Phone("갤럭시S4", "phone04.png"));
		phoneList.add(new Phone("갤럭시S5", "phone05.png"));
		phoneList.add(new Phone("갤럭시S6", "phone06.png"));
		phoneList.add(new Phone("갤럭시S7", "phone07.png"));
		
		//리스트에 있는 값 == TableView의 칼럼과 매핑
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory<>("smartPhone"));
		
		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));
		
		tcSmartPhone.setStyle("-fx-alignment: CENTER;");
		tcImage.setStyle("-fx-alignment: CENTER;");
		
		tableView.setItems(phoneList);
		
		tableView.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> obs, Phone oldV, Phone newV) {
				imageView.setImage(new Image("basic/images/" + newV.getImage()));
			}
		});
	}
	
}
