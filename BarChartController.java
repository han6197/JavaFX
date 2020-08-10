package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import model.Component;

public class BarChartController {

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private CategoryAxis xAxis;

	private ObservableList<String> firstCharacter = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		for (int i = 65; i < 91; i++) {
			firstCharacter.add((char) i + "");
			System.out.println((char) i);
		}
		xAxis.setCategories(firstCharacter);
	}

	public void setComponentList(List<Component> componentList) {
		int[] counters = new int[26];
		for (Component component : componentList) {
			char character = component.getCnumber().charAt(0);
			counters[character - 65]++;
		}

		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		for (int i = 0; i < counters.length; i++) {
			series.getData().add(new XYChart.Data<>(firstCharacter.get(i) + "", counters[i]));
		}

		barChart.getData().add(series);
	}
}