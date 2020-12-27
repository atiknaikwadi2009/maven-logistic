package at.sipovsven.GetIt.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.model.Order;
import at.sipovsven.GetIt.repository.OrderRepositoryJPA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MyOrderController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private TableView<Order> orderView;

	@FXML
	private TableColumn<Order, Integer> invoiceIdView;

	@FXML
	private TableColumn<Order, String> InvoiceNameView;

	@FXML
	private TableColumn<Order, String> invoiceDateView;

	@FXML
	private TableColumn<Order, String> invoiceCustomerView;

	@FXML
	private TableColumn<Order, Integer> invoicePaidView;
	@FXML
	private JFXButton downloadButton;

	@FXML
	private JFXButton deleteInvoiceButton;

	@FXML
	private JFXButton newOrderButton;

	@FXML
	private Label orderLbl;

	private ObservableList<Order> orderList = FXCollections.observableArrayList();

	OrderRepositoryJPA orderRepo = new OrderRepositoryJPA();

	@FXML
	void initialize() {
		orderList = (ObservableList<Order>) orderRepo.getAllOrders();

		orderView.setItems(orderList);

		invoiceIdView.setCellValueFactory(new PropertyValueFactory<Order, Integer>("ID"));
		InvoiceNameView.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
		invoiceDateView.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
		invoiceCustomerView.setCellValueFactory(new PropertyValueFactory<Order, String>("customer"));
		invoicePaidView.setCellValueFactory(new PropertyValueFactory<Order, Integer>("paid"));
	}

	@FXML
	void clearMouse(MouseEvent event) {

	}

	@FXML
	void deleteInvoice(ActionEvent event) {

	}

	@FXML
	void downloadInvoice(ActionEvent event) {

	}

	@FXML
	void newOrder(ActionEvent event) {

	}

}
