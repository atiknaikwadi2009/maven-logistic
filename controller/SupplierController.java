package at.sipovsven.GetIt.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.model.Supplier;
import at.sipovsven.GetIt.repository.SupplierRepositoryJPA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SupplierController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private TableView<Supplier> supplierView;

	@FXML
	private TableColumn<Supplier, Integer> supplierIdView;

	@FXML
	private TableColumn<Supplier, String> supplierNameView;

	@FXML
	private TableColumn<Supplier, String> supplierAddressView;

	@FXML
	private TableColumn<Supplier, String> supplierMailView;

	@FXML
	private TableColumn<Supplier, String> supplierPhoneView;

	@FXML
	private ImageView imgLogo;

	@FXML
	private JFXButton submitCustomerButton;

	@FXML
	private JFXButton deleteSupplierButton;

	@FXML
	private TextField supplierNameTxtField;

	@FXML
	private TextField supplierAddressTxtField;

	@FXML
	private TextField supplierEmailTxtField;

	@FXML
	private TextField supplierPhoneTxtField;

	@FXML
	private JFXButton createOrderButton;

	@FXML
	private TextField supplierNotesTxtField;

	SupplierRepositoryJPA supRepo = new SupplierRepositoryJPA();
	private ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
	Sceneswitcher sceneswitcher = new Sceneswitcher();
	
	Boolean pressed;
	
	
	@FXML
	private void initialize() {
		supplierList = FXCollections.observableList(supRepo.getAllSuppliers());

		supplierView.setItems(supplierList);

		supplierIdView.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("supplier_id"));
		supplierNameView.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
		supplierAddressView.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
		supplierMailView.setCellValueFactory(new PropertyValueFactory<Supplier, String>("email"));
		supplierPhoneView.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));
	}

	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
	}

	@FXML
	void deleteSupplier(ActionEvent event) {
		Supplier supplier = supplierView.getSelectionModel().getSelectedItem();

		supRepo.removeSupplier(supplier.getSupplier_id());
		supplierList.remove(supplier);
	}

	@FXML
	void openSupplierOrder(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("SupplierOrder", "Order supplies",pressed);
	}

	@FXML
	void submitSupplier(ActionEvent event) {

		Supplier supplier = new Supplier(supplierNameTxtField.getText(), supplierAddressTxtField.getText(),
				supplierEmailTxtField.getText(), supplierPhoneTxtField.getText());
		supRepo.addSupplier(supplier);
		supplierList.add(supplier);
		clean();

	}

	private void clean(){
		supplierNameTxtField.clear();
		supplierAddressTxtField.clear();
		supplierEmailTxtField.clear();
		supplierPhoneTxtField.clear();
	    	
	    }
}
