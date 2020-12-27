package at.sipovsven.GetIt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name = "tbl_supplier")
public class Supplier {

	
	private IntegerProperty supplier_id = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty address = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty phone = new SimpleStringProperty();
	
	public Supplier() {
		// TODO Auto-generated constructor stub
	}
	
	public Supplier(String name, String address, String email, String phone) {
		
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSupplier_id() {
		return supplier_idProperty().get();
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_idProperty().set(supplier_id);
	}

	public IntegerProperty supplier_idProperty() {
		return supplier_id;
	}

	@Column(name="name")
	public String getName() {
		return nameProperty().get();
	}

	public void setName(String name) {
		this.nameProperty().set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	@Column(name="address")
	public String getAddress() {
		return addressProperty().get();
	}

	public void setAddress(String adress) {
		this.addressProperty().set(adress);
	}

	public StringProperty addressProperty() {
		return address;
	}
	@Column(name="email")
	public String getEmail() {
		return emailProperty().get();
	}

	public void setEmail(String email) {
		this.emailProperty().set(email);
	}

	public StringProperty emailProperty() {
		return email;
	}
	@Column(name="phone")
	public String getPhone() {
		return phoneProperty().get();
	}

	public void setPhone(String phone) {
		this.phoneProperty().set(phone);
	}

	public StringProperty phoneProperty() {
		return phone;
	}

	
	
}
