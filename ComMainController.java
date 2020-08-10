package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.Component;

public class ComMainController {

	@FXML
	private TableView<Component> comTable;
	@FXML
	private TableColumn<Component, String> cname;
	@FXML
	private TableColumn<Component, String> cnumber;
	@FXML
	private TableColumn<Component, String> ccount;
	@FXML
	private TableColumn<Component, String> cloc;

	private Main main;

	@FXML
	private void initialize() {
		cname.setCellValueFactory(cellData -> cellData.getValue().getCnameProperty());
		cnumber.setCellValueFactory(cellData -> cellData.getValue().getCnumberProperty());
		ccount.setCellValueFactory(cellData -> cellData.getValue().getCcountProperty());
		cloc.setCellValueFactory(cellData -> cellData.getValue().getClocProperty());
	}

	public void setMain(Main main) {
		this.main = main;
		comTable.setItems(main.getComponentList());
	}

	public ComMainController() {
	}

//	@FXML
//	private void selectAction() {
//		Component component = new Component("", "", "", "");
//		int returnValue2 = main.setSelectView(component);
//		if (returnValue2 == 1) {
//			System.out.println(component);
//		}
//	}

	@FXML
	private void addAction() {
		Component component = new Component("", "", "", "");
		int returnValue = main.setComponentDataView(component);
		if (returnValue == 1) {
			main.getComponentList().add(component);
		}
	}

	@FXML
	private void editAction() {
		Component component = comTable.getSelectionModel().getSelectedItem();
		if (component != null) {
			main.setComponentDataView(component);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("오류 메시지");
			alert.setHeaderText("선택 오류가 발생했습니다.");
			alert.setContentText("수정 단어를 선택해주세요.");
			alert.showAndWait();
		}
	}

	@FXML
	private void deleteAction() {
		int selectedIndex = comTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			comTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("오류 메시지");
			alert.setHeaderText("선택 오류가 발생했습니다.");
			alert.setContentText("삭제할 단어를 선택해주세요.");
			alert.showAndWait();
		}
	}
}