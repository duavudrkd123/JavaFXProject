package basic.container.eventhandle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;

public class RootController implements Initializable {
	@FXML Label label;
	@FXML Slider slider;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {//화면이 로딩될 때 처리할 것들
		slider.valueProperty().addListener(new ChangeListener<Number>() {//속성값이 바뀔 때 마다 실행

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number startValue, Number endValue) {
				System.out.println("startValue: "+startValue.doubleValue());
				System.out.println("endValue: "+endValue.doubleValue());
				label.setFont(new Font(endValue.doubleValue()));
			}
			
		});
	}
	
}
