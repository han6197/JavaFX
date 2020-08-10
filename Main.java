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
		this.primaryStage.setTitle("차량 부속품 관리프로그램");
		setRootLayout();
		setComMainView();
	}

	// RootRayout 초기화
	public void setRootLayout() {
		try {
			// FXML 파일을 이용해 루트 레이아웃 import
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// 루트 레이아웃을 포함하는 Scene을 보여줍니다.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// RootRayout 안에 Main 페이지
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

	// 현재의 메인 스테이지를 반환
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
			dialogStage.setTitle("부품 추가");
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
//			dialogStage.setTitle("부품 조회");
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
			alert.setTitle("성공 메시지");
			alert.setHeaderText("성공적으로 수행했습니다.");
			alert.setContentText("데이터베이스에 성공적으로 접근했습니다.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(primaryStage);
			alert.setTitle("오류 메시지");
			alert.setHeaderText("오류가 발생했습니다.");
			alert.setContentText("데이터베이스에 오류가 발생했습니다.");
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
		alert.setTitle("알아보기");
		alert.setHeaderText("< 프로그램 정보 >");
		alert.setContentText("프로그램 버전 : 1.0 Ver\n" + "프로그램 개발자: 한진수\n" + "프로그램 설명 : 자바FX DB프로그램입니다.");
		alert.showAndWait();
	}

	@FXML
	private void barChartAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/BarChartView.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();

			Stage stage = new Stage();
			stage.setTitle("위치별 부문 수량");
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