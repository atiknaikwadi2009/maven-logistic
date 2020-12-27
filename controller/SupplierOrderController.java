package at.sipovsven.GetIt.controller;

import java.io.IOException;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.model.OrderedProduct;
import at.sipovsven.GetIt.model.Product;
import at.sipovsven.GetIt.repository.OrderedProductRepositoryJPA;
import at.sipovsven.GetIt.repository.ProductRepositoryJPA;
import at.sipovsven.GetIt.service.NotificationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SupplierOrderController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private JFXButton cancelButton;

	@FXML
	private JFXButton orderProducts;

	@FXML
	private JFXButton addProductButton;

	@FXML
	private TextField productSearchField;

	@FXML
	private TextField quantityTxtField;

	@FXML
	private TableView<OrderedProduct> orderView;

	@FXML
	private TableColumn<OrderedProduct, Integer> idView;

	@FXML
	private TableColumn<OrderedProduct, String> productView;

	@FXML
	private TableColumn<OrderedProduct, Integer> quantityView;

	@FXML
	private JFXButton acceptOrderedProduct;

	@FXML
	private JFXButton changeQuantityBtn;

	@FXML
	private TextField changeQtyTxtField;

	NotificationService notification = new NotificationService();
	ProductRepositoryJPA productRepo = new ProductRepositoryJPA();
	OrderedProductRepositoryJPA orderedProdRepo = new OrderedProductRepositoryJPA();

	private ObservableList<Product> orderList = FXCollections.observableArrayList();;
	private ObservableList<OrderedProduct> orderedProductList = FXCollections.observableArrayList();

	Product product = new Product();
	OrderedProduct orderedProduct = new OrderedProduct();

	@FXML
	void initialize() {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(productSearchField, productRepo.getAllProductNames());

		orderedProductList = FXCollections.observableArrayList(orderedProdRepo.getAllOrderedProducts());
		orderView.setItems(orderedProductList);

		idView.setCellValueFactory(new PropertyValueFactory<OrderedProduct, Integer>("id"));
		productView.setCellValueFactory(new PropertyValueFactory<OrderedProduct, String>("name"));
		quantityView.setCellValueFactory(new PropertyValueFactory<OrderedProduct, Integer>("quantity"));

	}

	@FXML
	void accept(ActionEvent event) {
		try {
			orderedProduct = orderedProdRepo.find(orderView.getSelectionModel().getSelectedItem().getId());

			String name = orderedProduct.getName();
			product = productRepo.getProductByName(name);

			int id = product.getId();
			int quantity = product.getQuantity();
			productRepo.persistNewQuantity(id,
					orderView.getSelectionModel().getSelectedItem().getQuantity() + quantity);

			orderedProdRepo.removeOrderedProduct(id);
			orderedProductList.remove(orderedProduct);
			orderedProductList = FXCollections.observableArrayList(orderedProdRepo.getAllOrderedProducts());
			orderView.setItems(orderedProductList);

			notification.showNotification("Success", product.getName() + "was successfully added to Database",
					backGround);
		} catch (Exception e) {

			notification.showErrorMessage("Error", product.getName() + "was not added to Database", backGround);

		}

	}

	@FXML
	void addProduct(ActionEvent event) {
		product = productRepo.getProductByName(productSearchField.getText());
		product.setQuantity(Integer.parseInt(quantityTxtField.getText()));
		orderList.add(product);

		OrderedProduct orderedProduct2 = new OrderedProduct();

		orderedProduct2.setId(product.getId());
		orderedProduct2.setName(product.getName());
		orderedProduct2.setCategory(product.getCategory());
		orderedProduct2.setQuantity(product.getQuantity());
		orderedProduct2.setWeight(product.getWeight());
		orderedProduct2.setTax(product.getTax());
		orderedProduct2.setPrice(product.getPrice());
		orderedProduct2.setPurchasePrice(product.getPurchasePrice());

		orderedProductList.add(orderedProduct2);
		orderedProdRepo.addOrderedProduct(orderedProduct2);
	}

	@FXML
	void delete(ActionEvent event) throws IOException {
		orderedProductList.remove(orderView.getSelectionModel().getSelectedItem());
		orderedProdRepo.removeOrderedProduct(orderView.getSelectionModel().getSelectedItem().getId());
		
		notification.showNotification("Success", orderView.getSelectionModel().getSelectedItem().getName() + " was successfully deleted",
				backGround);
	}

	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
	}

}
