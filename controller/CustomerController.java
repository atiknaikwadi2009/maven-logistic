package at.sipovsven.GetIt.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.model.Customer;
import at.sipovsven.GetIt.repository.CustomerRepositoryJPA;
import at.sipovsven.GetIt.service.NotificationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private TableView<Customer> customerView;

	@FXML
	private TableColumn<Customer, Integer> customerIdView;

	@FXML
	private TableColumn<Customer, String> customerNameView;

	@FXML
	private TableColumn<Customer, String> customerLastNameView;

	@FXML
	private TableColumn<Customer, String> customerAdressView;

	@FXML
	private TableColumn<Customer, String> customerEmailView;

	@FXML
	private TableColumn<Customer, String> customerPhoneView;

	@FXML
	private ImageView imgLogo;

	@FXML
	private JFXButton submitCustomerButton;

	@FXML
	private JFXButton deleteCustomerButton;

	@FXML
	private TextField customerNameTxtField;

	@FXML
	private TextField customerLastNameTxtField;

	@FXML
	private TextField customerAdressTxtField;

	@FXML
	private TextField customerEmailTxtField;

	@FXML
	private TextField customerPhoneTxtField;
	@FXML
	private JFXButton createOrderButton;

	Sceneswitcher sceneswitcher = new Sceneswitcher();
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	CustomerRepositoryJPA customerRepo = new CustomerRepositoryJPA();
	Alert a;
	NotificationService notification = new NotificationService();
	boolean b;

	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
	}

	@FXML
	void deleteCustomer(ActionEvent event) {
		Customer selectedCustomer = customerView.getSelectionModel().getSelectedItem();
		customerRepo.removeCustomer(selectedCustomer.getCustomer_id());
		customerList.removeAll(selectedCustomer);
	}

	@FXML
	void initialize() {

		customerList = FXCollections.observableList(customerRepo.getAllCustomers());

		customerView.setItems(customerList);

		customerIdView.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customer_id"));
		customerNameView.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		customerLastNameView.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastname"));
		customerAdressView.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		customerEmailView.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		customerPhoneView.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
	}

	@FXML
	void submitCustomer(ActionEvent event) throws Exception {
		try {
			Customer customer = new Customer(customerNameTxtField.getText(), customerLastNameTxtField.getText(),
					customerAdressTxtField.getText(), customerEmailTxtField.getText(), customerPhoneTxtField.getText());

			if (validate() == false) {
				notification.showErrorMessage("Error", "Please enter the right input", backGround);
			}

			else {
				customerRepo.addCustomers(customer);
				customerList.add(customer);
				notification.showNotification("Success",
						"Customer: " + customerNameTxtField.getText() + " added to Database!", backGround);
				clean();
			}

		} catch (Exception e) {

			notification.showErrorMessage("Error", "Something went wrong. Please try again", backGround);
		}
	}

	private void clean() {
		// TODO Auto-generated method stub
		customerNameTxtField.clear();
		customerLastNameTxtField.clear();
		customerAdressTxtField.clear();
		customerEmailTxtField.clear();
		customerPhoneTxtField.clear();
	}

	public int getCustomerId(int id) {
		return id = customerView.getSelectionModel().getSelectedItem().getCustomer_id();
	}

	@FXML
	void openOrderWIndow(ActionEvent event) throws Exception {
		
		Scene s = backGround.getScene();System.out.println(s.getStylesheets());
		String string = "file:/C:/Users/svens/eclipse-workspace/GetIt/target/classes/css/darktheme.css";
		if(s.getStylesheets().contains(string)){
			
			System.out.println(s.getStylesheets());
			b = false;
		}
		
		else {
			b = true;
		}
			if (b == true) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/CreateOrder.fxml"));
				Stage stage = new Stage();
				Parent root = loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				stage.setTitle("Create Order");
				stage.setScene(scene);
				stage.show();
				CreateOrderController orderController = loader.getController();
				orderController.transferMessage(customerView.getSelectionModel().getSelectedItem().getCustomer_id());
			}

			else {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/fxml/CreateOrder.fxml"));
				Stage stage = new Stage();
				Parent root = loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/css/darktheme.css").toExternalForm());
				stage.setTitle("Create Order");
				stage.setScene(scene);
				stage.show();
				CreateOrderController orderController = loader.getController();
				orderController.transferMessage(customerView.getSelectionModel().getSelectedItem().getCustomer_id());
			}
	}

	public boolean validate() {

		if (customerNameTxtField.getText().trim().isEmpty()) {
			return false;
		}

		if (customerAdressTxtField.getText().trim().isEmpty()) {
			return false;
		}
		if (customerEmailTxtField.getText().trim().isEmpty()) {
			return false;
		}
		if (customerLastNameTxtField.getText().trim().isEmpty()) {
			return false;
		}
		if (customerNameTxtField.getText().trim().isEmpty()) {
			return false;
		}
		if (customerPhoneTxtField.getText().trim().isEmpty()) {
			return false;
		}

		return true;
	}

}
