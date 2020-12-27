package at.sipovsven.GetIt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name = "tbl_ordered_product")
public class OrderedProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private IntegerProperty quantity = new SimpleIntegerProperty();
	private DoubleProperty weight = new SimpleDoubleProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private DoubleProperty purchasePrice = new SimpleDoubleProperty();
	private StringProperty category = new SimpleStringProperty();
	private DoubleProperty tax = new SimpleDoubleProperty();

//	private Set<Category> category;

	public OrderedProduct() {

	}

	public OrderedProduct(String name, int quantity, double weight, double price, double purchasePrice, String category,
			double tax) {
		this.name = new SimpleStringProperty(name);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.weight = new SimpleDoubleProperty(weight);
		this.price = new SimpleDoubleProperty(price);
		this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
		this.category = new SimpleStringProperty(category);
		this.tax = new SimpleDoubleProperty(tax);
	}

	public String intToString(int x) {
		String quantityString = Integer.toString(x);
		return quantityString;
	}

	public String doubleToString(double y) {
		String priceString = Double.toString(y);
		return priceString;
	}

	@Id
	public int getId() {
		return idProperty().get();
	}

	public void setId(int id) {
		this.idProperty().set(id);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return nameProperty().get();
	}

	public void setName(String name) {
		this.nameProperty().set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return quantityProperty().get();
	}

	public void setQuantity(int quantity) {
		this.quantityProperty().set(quantity);
	}

	public IntegerProperty quantityProperty() {
		return quantity;
	}

	@Column(name = "weight", nullable = false)
	public double getWeight() {
		return weightProperty().get();
	}

	public void setWeight(double weight) {
		this.weightProperty().set(weight);
	}

	public DoubleProperty weightProperty() {
		return weight;
	}

	@Column(name = "price", nullable = false)
	public double getPrice() {
		return priceProperty().get();
	}

	public void setPrice(double price) {
		this.priceProperty().set(price);
	}

	public DoubleProperty priceProperty() {
		return price;
	}

	@Column(name = "p_price")
	public double getPurchasePrice() {
		return purchasePriceProperty().get();
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePriceProperty().set(purchasePrice);
	}

	public DoubleProperty purchasePriceProperty() {
		return purchasePrice;
	}

	@Column(name = "category", nullable = false)
	public String getCategory() {
		return categoryProperty().get();
	}

	public void setCategory(String category) {
		this.categoryProperty().set(category);
	}

	public StringProperty categoryProperty() {
		return category;
	}

	@Column(name = "tax", nullable = false)
	public double getTax() {
		return taxProperty().get();
	}

	public void setTax(double tax) {
		this.taxProperty().set(tax);
	}

	public DoubleProperty taxProperty() {
		return tax;
	}

}
