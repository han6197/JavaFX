package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Component;

public class ComDataController {

	@FXML
	private TextField cnameField;
	@FXML
	private TextField cnumberField;
	@FXML
	private TextField ccountField;
	@FXML
	private TextField clocField;

	private Stage dialogStage;
	private Component component;
	private int returnValue = 0;
	
	@FXML
	private void initialize() {
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setComponent(Component component) {
		this.component = component;
		cnameField.setText(component.getCname());
		cnumberField.setText(component.getCnumber());
		ccountField.setText(component.getCcount());
		clocField.setText(component.getCloc());
	}
	
	public int getReturnValue() {
		return returnValue;
	}

	@FXML
	private void confirmAction() {
		if(valid()) {
			component.setCname(cnameField.getText());
			component.setCnumber(cnumberField.getText());
			component.setCcount(ccountField.getText());
			component.setCloc(clocField.getText());
			returnValue = 1;
			dialogStage.close();
		}
	}

	@FXML
	private void cancelAction() {
		dialogStage.close();
	}
	
	private boolean valid() {
		String errorMessage = "";
		if(cnameField.getText() == null || cnameField.getText().equals("")) {
			errorMessage += "��ǰ �̸��� �Է��ϼ���.\n";
		}
		if(cnumberField.getText() == null || cnumberField.getText().equals("")) {
			errorMessage += "��ǰ �ڵ带 �Է��ϼ���.\n";
		}
		if(ccountField.getText() == null || ccountField.getText().equals("")) {
			errorMessage += "��ǰ ������ �Է��ϼ���.\n";
		}
		if(errorMessage.equals("")) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("���� �޽���");
			alert.setHeaderText("���� ����� �Է����ּ���.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
}