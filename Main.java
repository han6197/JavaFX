package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Component;
import model.ComponentDAO;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	public static ObservableList<Component> componentList = FXCollections.observableArrayList();

	public Main() {
	}

	public ObservableList<Component> getComponentList() {
		return componentList;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("���� �μ�ǰ �������α׷�");
		setRootLayout();
		setComMainView();
	}

	// RootRayout �ʱ�ȭ
	public void setRootLayout() {
		try {
			// FXML ������ �̿��� ��Ʈ ���̾ƿ� import
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// ��Ʈ ���̾ƿ��� �����ϴ� Scene�� �����ݴϴ�.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// RootRayout �ȿ� Main ������
	public void setComMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ComMainView.fxml"));
			AnchorPane comMainView = (AnchorPane) loader.load();
			rootLayout.setCenter(comMainView);

			ComMainController controller = loader.getController();
			controller.setMain(this);

			ComponentDAO componentDAO = new ComponentDAO();
			ObservableList<Component> tempList = componentDAO.getComponentList();
			for (int i = 0; i < tempList.size(); i++) {
				componentList.add(tempList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ������ ���� ���������� ��ȯ
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public int setComponentDataView(Component component) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/ComDataView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("��ǰ �߰�");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ComDataController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setComponent(component);

			dialogStage.showAndWait();
			return controller.getReturnValue();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
//	public int setSelectView(Component component) {
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Main.class.getResource("../view/SelectView.fxml"));
//			AnchorPane paze = (AnchorPane) loader.load();
//
//			Stage dialogStage = new Stage();
//			dialogStage.setTitle("��ǰ ��ȸ");
//			dialogStage.initModality(Modality.WINDOW_MODAL);
//			dialogStage.initOwner(primaryStage);
//			Scene scene = new Scene(paze);
//			dialogStage.setScene(scene);
//
//			SelectController controller = loader.getController();
//			controller.setDialogStage2(dialogStage);
//			controller.setComponent(component);
//
//			dialogStage.showAndWait();
//			return controller.getReturnValue();
//		} catch (IOException e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}

	@FXML
	private void saveAction() {
		ComponentDAO componentDAO = new ComponentDAO();
		int result = componentDAO.saveComponentList(componentList);
		if (result == 1) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(primaryStage);
			alert.setTitle("���� �޽���");
			alert.setHeaderText("���������� �����߽��ϴ�.");
			alert.setContentText("�����ͺ��̽��� ���������� �����߽��ϴ�.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(primaryStage);
			alert.setTitle("���� �޽���");
			alert.setHeaderText("������ �߻��߽��ϴ�.");
			alert.setContentText("�����ͺ��̽��� ������ �߻��߽��ϴ�.");
			alert.showAndWait();
		}
	}

	@FXML
	private void exitAction() {
		System.exit(1);
	}

	@FXML
	private void aboutAction() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("�˾ƺ���");
		alert.setHeaderText("< ���α׷� ���� >");
		alert.setContentText("���α׷� ���� : 1.0 Ver\n" + "���α׷� ������: ������\n" + "���α׷� ���� : �ڹ�FX DB���α׷��Դϴ�.");
		alert.showAndWait();
	}

	@FXML
	private void barChartAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/BarChartView.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();

			Stage stage = new Stage();
			stage.setTitle("��ġ�� �ι� ����");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			BarChartController controller = loader.getController();
			controller.setComponentList(componentList);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}