package at.sipovsven.GetIt.controller;

import java.sql.Connection;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;

import at.sipovsven.GetIt.model.Product;
import at.sipovsven.GetIt.repository.CategoryRepositoryJPA;
import at.sipovsven.GetIt.repository.ProductRepositoryJPA;
import at.sipovsven.GetIt.service.NotificationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProductController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private TableView<Product> tableView;

	@FXML
	private TableColumn<Product, Integer> idView;

	@FXML
	private TableColumn<Product, String> nameView;

	@FXML
	private TableColumn<Product, Integer> quantityView;

	@FXML
	private TableColumn<Product, Double> weigthView;

	@FXML
	private TableColumn<Product, Double> priceView;

	@FXML
	private TableColumn<Product, Double> purchasePriceView;

	@FXML
	private TableColumn<Product, String> categoryView;

	@FXML
	private TableColumn<Product, Double> taxView;

	@FXML
	private ImageView imgLogo;

	@FXML
	private JFXButton submitButton;

	@FXML
	private JFXButton deleteButton;

	@FXML
	private JFXComboBox<String> categoryComboBox;
	@FXML
	private JFXRadioButton tenTaxRadioButton;

	@FXML
	private JFXRadioButton twentyTaxRadioButton;

	@FXML
	private TextField nameTxtField;

	@FXML
	private TextField qtyTxtField;

	@FXML
	private TextField weightTxtField;

	@FXML
	private TextField purchaseTxtField;

	@FXML
	private TextField SellingTxtField;

	@FXML
	private JFXButton addCategoryButton;
	
	
	boolean pressed;

	Sceneswitcher sceneSwitcher = new Sceneswitcher();
	ProductRepositoryJPA productRepo = new ProductRepositoryJPA();
	CategoryRepositoryJPA cateRepo = new CategoryRepositoryJPA();

	private ObservableList<String> categoryList;

	Alert a = new Alert(AlertType.NONE);
	Connection con;
	Statement stmt;
	private ObservableList<Product> productList = FXCollections.observableArrayList();
	NotificationService notification = new NotificationService();

	@FXML
	void initialize() {

		productList = FXCollections.observableArrayList(productRepo.getAllProducts());
		categoryList = FXCollections.observableArrayList(cateRepo.getAllCategoryNames());

		categoryComboBox.setItems(categoryList);

		tableView.setItems(productList);

		idView.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
		nameView.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		quantityView.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		weigthView.setCellValueFactory(new PropertyValueFactory<Product, Double>("weight"));
		priceView.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
		purchasePriceView.setCellValueFactory(new PropertyValueFactory<Product, Double>("purchasePrice"));
		categoryView.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		taxView.setCellValueFactory(new PropertyValueFactory<Product, Double>("tax"));

	}

	@FXML
	void chooseCategoryAction(ActionEvent event) {

	}

	@FXML
	void chooseTax(ActionEvent event) {

	}

	@FXML
	void deleteOnClick(ActionEvent event) {
		Product selectedProduct = tableView.getSelectionModel().getSelectedItem();
		productRepo.removeProduct(selectedProduct.getId());
		productList.remove(selectedProduct);
	}

	@FXML
	void submit(ActionEvent event) throws Exception {
		try {

			double radioValue = 20.0;
			if (tenTaxRadioButton.isSelected()) {
				radioValue = 10.0;
			}

			Product product = new Product(nameTxtField.getText(), Integer.parseInt(qtyTxtField.getText()),
					Double.parseDouble(weightTxtField.getText()), Double.parseDouble(SellingTxtField.getText()),
					Double.parseDouble(purchaseTxtField.getText()),
					categoryComboBox.getSelectionModel().getSelectedItem(), radioValue);

			if (validate() == false) {

			}

			else {
				productRepo.addProduct(product);
				productList.add(product);

				notification.showNotification("Success!", "Product: " + nameTxtField.getText() + " added to Database!",
						backGround);
				clean();
			}

		} catch (Exception e) {

			notification.showErrorMessage("Error!", "Please check if every Field is getting the right Input!",
					backGround);
		}
	}

	@FXML
	void searchItem(ActionEvent event) {

	}

	@FXML
	void cleanTaxOnTen(ActionEvent event) {
		tenTaxRadioButton.setSelected(false);
	}

	@FXML
	void cleanTaxOnTwenty(ActionEvent event) {
		twentyTaxRadioButton.setSelected(false);
	}

	@FXML
	void openCategory(ActionEvent event) throws Exception {

		Scene s = backGround.getScene();
		
		if (s.getStylesheets().contains("file:/C:/Users/svens/eclipse-workspace/GetIt/target/classes/css/darktheme.css")) {
			pressed = false;
		}
		else {
			pressed = true;
		}
		sceneSwitcher.openNewWindow("CreateNewCategory", "New Category",pressed);
	}

	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
	}

	private void clean() {
		// cleans textfields

		tenTaxRadioButton.setSelected(false);
		twentyTaxRadioButton.setSelected(false);
		nameTxtField.clear();
		qtyTxtField.clear();
		weightTxtField.clear();
		purchaseTxtField.clear();
		SellingTxtField.clear();

	}

	public boolean validate() {

		if (nameTxtField.getText().trim().isEmpty()) {
			return false;
		}

		if (qtyTxtField.getText().trim().isEmpty()) {
			return false;
		}
		if (weightTxtField.getText().trim().isEmpty()) {
			return false;
		}
		if (purchaseTxtField.getText().trim().isEmpty()) {
			return false;
		}
		if (SellingTxtField.getText().trim().isEmpty()) {
			return false;
		}

		return true;
	}
}