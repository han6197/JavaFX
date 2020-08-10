package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComponentDAO {

	private Connection conn;
	private ResultSet rs;

	// DB 접속
	public ComponentDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr2", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DB 초기화
	public int deleteComponentList() {
		String SQL = "DELETE FROM COMPONENT";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// DB 삽입
	public int insertComponentList(ObservableList<Component> componentList) {
		String SQL = "INSERT INTO COMPONENT VALUES (?, ?, ?, ?)";
		try {
			int i;
			for (i = 0; i < componentList.size(); i++) {
				Component component = componentList.get(i);
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, component.getCname());
				pstmt.setString(2, component.getCnumber());
				pstmt.setString(3, component.getCcount());
				pstmt.setString(4, component.getCloc());
				pstmt.executeUpdate();
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

//	// DB 조회
//	public void selectComponentList(List<Component> cnumber) {
//		String SQL = "SELECT * FROM COMPONENT WHERE CNUMBER=?";
//		try {
//			Component component = cnumber.get(0);
//			PreparedStatement pstmt = conn.prepareStatement(SQL);
//			rs = pstmt.executeQuery();
//			pstmt.setString(1, component.getCnumber());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	// DB 출력
	public ObservableList<Component> getComponentList() {
		String SQL = "SELECT * FROM COMPONENT";
		ObservableList<Component> componentList = FXCollections.observableArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Component component = new Component(rs.getString("부품이름"), rs.getString("부품코드"), rs.getString("수량"),
						rs.getString("위치"));
				componentList.add(component);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return componentList;
	}

	// DB에 저장
	public int saveComponentList(ObservableList<Component> componentList) {
		if (deleteComponentList() == -1) {
			return -1;
		}
		if (insertComponentList(componentList) == -1) {
			return -1;
		}
		return 1;
	}
}