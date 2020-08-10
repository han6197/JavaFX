package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Component {

	private final StringProperty cname;
	private final StringProperty cnumber;
	private final StringProperty ccount;
	private final StringProperty cloc;

	public Component(String cname, String cnumber, String ccount, String cloc) {
		this.cname = new SimpleStringProperty(cname);
		this.cnumber = new SimpleStringProperty(cnumber);
		this.ccount = new SimpleStringProperty(ccount);
		this.cloc = new SimpleStringProperty(cloc);
	}

	public String getCname() {
		return cname.get();
	}

	public void setCname(String cname) {
		this.cname.set(cname);
	}

	public StringProperty getCnameProperty() {
		return cname;
	}

	public String getCnumber() {
		return cnumber.get();
	}

	public void setCnumber(String cnumber) {
		this.cnumber.set(cnumber);
	}

	public StringProperty getCnumberProperty() {
		return cnumber;
	}

	public String getCcount() {
		return ccount.get();
	}

	public void setCcount(String ccount) {
		this.ccount.set(ccount);
	}

	public StringProperty getCcountProperty() {
		return ccount;
	}
	
	public String getCloc() {
		return cloc.get();
	}

	public void setCloc(String cloc) {
		this.cloc.set(cloc);
	}

	public StringProperty getClocProperty() {
		return cloc;
	}
}