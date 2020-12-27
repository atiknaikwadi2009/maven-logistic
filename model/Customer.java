package at.sipovsven.GetIt.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Entity
@Table(name="tbl_customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private IntegerProperty customer_id = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty lastname = new SimpleStringProperty();
	private StringProperty address = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty phone = new SimpleStringProperty();
	public Set<Order> order;

	
	public Customer() {

	}

	
	public Customer(int customer_id, String name, String lastname, String address,
			String email, String phone, Set<Order> order) {
		super();
		setCustomer_id(customer_id);
		setName(name);
		setLastname(lastname);
		setAddress(address);
		setEmail(email);
		setPhone(phone);
		
	}

	
	public Customer(String name, String lastname, String address, String email, String phone) {
		this.name = new SimpleStringProperty(name);
		this.lastname = new SimpleStringProperty(lastname);
		this.address = new SimpleStringProperty(address);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCustomer_id() {
		return customer_idProperty().get();
	}

	public void setCustomer_id(int customer_id) {
		this.customer_idProperty().set(customer_id);
	}

	public IntegerProperty customer_idProperty() {
		return customer_id;
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
	@Column(name="lastname")
	public String getLastname() {
		return lastNameProperty().get();
	}

	public void setLastname(String lastname) {
		this.lastNameProperty().set(lastname);
	}

	public StringProperty lastNameProperty() {
		return lastname;
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
		return addressProperty().get();
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

	@OneToMany(mappedBy = "customer")
	public Set<Order> getOrders() {
		return order;
	}

	public void setOrders(Set<Order> invoice) {
		this.order = invoice;
	}

}
