package at.sipovsven.GetIt.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.model.Customer;
import at.sipovsven.GetIt.model.Order;
import at.sipovsven.GetIt.model.Owner;
import at.sipovsven.GetIt.model.Product;
import at.sipovsven.GetIt.repository.CreateOrderRepositoryJPA;
import at.sipovsven.GetIt.repository.CustomerRepositoryJPA;
import at.sipovsven.GetIt.repository.OrderRepositoryJPA;
import at.sipovsven.GetIt.repository.OwnerRepositoryJPA;
import at.sipovsven.GetIt.repository.ProductRepositoryJPA;
import at.sipovsven.GetIt.service.NotificationService;
import at.sipovsven.GetIt.service.PdfService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class CreateOrderController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private JFXButton cancelButton;

	@FXML
	private JFXButton createOrderButton;

	@FXML
	private JFXButton addProductButton;

	@FXML
	private TextField productSearchField;

	@FXML
	private TextField quantityTxtField;

	@FXML
	private TableView<Product> productView;
	@FXML
	private TableColumn<Product, String> nameView;

	@FXML
	private TableColumn<Product, Integer> quantityView;

	@FXML
	private TableColumn<Product, Double> priceView;

	@FXML
	private TextField customerNameTxtField;

	@FXML
	private TextField customerLastNameTxtField;

	@FXML
	private TextField customerAddressTxtField;

	@FXML
	private TextField customerEmailTxtField;

	@FXML
	private TextField customerPhoneTxtField;

	@FXML
	private TextField choosePathTxtField;

	@FXML
	private JFXButton choosePathButton;

	ProductRepositoryJPA productRepo = new ProductRepositoryJPA();
	OwnerRepositoryJPA ownerRepo = new OwnerRepositoryJPA();
	CustomerRepositoryJPA customerRepo = new CustomerRepositoryJPA();
	CreateOrderRepositoryJPA createOrderRepo = new CreateOrderRepositoryJPA();
	OrderRepositoryJPA orderRepo = new OrderRepositoryJPA();

	PdfService pdfRepo = new PdfService();
	NotificationService notification = new NotificationService();

	private Product product = new Product();
	private ObservableList<Product> orderedProducts = FXCollections.observableArrayList();;
	private ObservableList<Product> productList = FXCollections.observableArrayList();
	private List<Customer> customerList = FXCollections.observableArrayList();
	private ArrayList<Integer> productIdList = new ArrayList<Integer>();
	private List<Integer> quantityList = FXCollections.observableArrayList();

	private Double amount = 0.0;

	@FXML
	void initialize() {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(productSearchField, productRepo.getAllProductNames());

		productList = FXCollections.observableArrayList();
		productView.setItems(productList);

		nameView.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		quantityView.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		priceView.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
	}

	@FXML
	void addProduct(ActionEvent event) {

		product = productRepo.getProductByName(productSearchField.getText());
		product.setQuantity(Integer.parseInt(quantityTxtField.getText()));
		orderedProducts.add(product);
		productList.add(product);

		productView.setItems(productList);

		// add product IDS to ArrayList
		productIdList.add(product.getId());
		quantityList.add(Integer.parseInt(quantityTxtField.getText()));

		Product calcProduct = productRepo.getProductByName(product.getName());

		amount = amount + calcProduct.getPrice() * Integer.parseInt(quantityTxtField.getText());

		productSearchField.clear();
		quantityTxtField.clear();
	}

	@FXML
	void cancelOrder(ActionEvent event) {

	}

	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
	}

	@FXML
	void createOrder(ActionEvent event) throws MalformedURLException, DocumentException, IOException {
		try {

			Date date = new Date();
			int ownerId = ownerRepo.getLatestOwnerId();
			System.out.println("Owner" + ownerId);
			Owner owner = ownerRepo.getOwnerById(ownerId);
			if (owner == null) {
				notification.showErrorMessage("No Owner Found", "Please configure owner Settings", backGround);
				return;
			}
			Customer customer = customerList.get(0);

			int orderID = orderRepo.getLatestOrderId();
			Order order = new Order("order" + orderID, false, customer, date, amount);
			String savePath = choosePathTxtField.getText();
			productRepo.setOrderedQuantity(productList, productIdList, quantityList);
			orderRepo.addOrder(order);

			pdfRepo.createPdf(savePath, "Invoice", orderID, customer, owner, orderedProducts);
			notification.showNotification("Success", "Order: " + orderID + " successfully created!", backGround);
			productList = null;
			clean();

		} catch (Exception e) {
			notification.showErrorMessage("Error", "Could not create Order, please check if a field is empty!",
					backGround);
		}
	}

	public int transferMessage(int customer_id) {
		// Display the message

		Customer customer = createOrderRepo.getCustomerForOrder(customer_id);
		customerList.add(customer);
		customerNameTxtField.setText(customer.getName());
		customerLastNameTxtField.setText(customer.getLastname());
		customerAddressTxtField.setText(customer.getAddress());
		customerEmailTxtField.setText(customer.getEmail());
		customerPhoneTxtField.setText(customer.getPhone());
		return customer_id;
	}
	
	
	@FXML
	void choosePath(ActionEvent event) throws IOException {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		Stage stage = (Stage) backGround.getScene().getWindow();
		File selectedDirectory = directoryChooser.showDialog(stage);

		if (selectedDirectory == null) {
			// No Directory selected

		} else {
			choosePathTxtField.setText((selectedDirectory.getAbsolutePath()));
		}
	}

	void clean() {
		customerNameTxtField.clear();
		customerLastNameTxtField.clear();
		customerAddressTxtField.clear();
		customerEmailTxtField.clear();
		customerPhoneTxtField.clear();
		choosePathTxtField.clear();
	}

}
