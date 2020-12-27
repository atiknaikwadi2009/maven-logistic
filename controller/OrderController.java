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

public class OrderController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private TableView<Order> orderView;

	@FXML
	private TableColumn<Order, Integer> invoiceIdView;

	@FXML
	private TableColumn<Order, String> InvoiceNameView;

	@FXML
	private TableColumn<Order, String> invoiceCustomerView;

	@FXML
	private TableColumn<Order, String> invoiceDateView;
	
	@FXML
	private TableColumn<Order, Double> amountView;

	@FXML
	private TableColumn<Order, Integer> invoicePaidView;
	@FXML
	private JFXButton downloadButton;

	@FXML
	private JFXButton deleteInvoiceButton;

	@FXML
	private JFXButton paidButton;

	@FXML
	private Label orderLbl;

	private ObservableList<Order> orderList = FXCollections.observableArrayList();

	OrderRepositoryJPA orderRepo = new OrderRepositoryJPA();

	@FXML
	void initialize() {
		orderList = FXCollections.observableArrayList(orderRepo.getAllOrders());
		
		orderView.setItems(orderList);

		invoiceIdView.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
		InvoiceNameView.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
		invoiceCustomerView.setCellValueFactory(new PropertyValueFactory<Order, String>("customer"));
		invoiceDateView.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
		amountView.setCellValueFactory(new PropertyValueFactory<Order, Double>("amount"));
		invoicePaidView.setCellValueFactory(new PropertyValueFactory<Order, Integer>("paid"));
	}

	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
	}

	@FXML
	void deleteInvoice(ActionEvent event) {

	}

	@FXML
	void downloadInvoice(ActionEvent event) {

	}

	@FXML
	void isPaid(ActionEvent event) throws Exception {
		Order order = orderView.getSelectionModel().getSelectedItem();
		int orderId = order.getOrderID();
		
		Order orderToChange = orderRepo.getOrderById(orderId);
		orderToChange.setPaid(true);
		orderRepo.updateOrder(orderToChange);
		orderList = FXCollections.observableArrayList(orderRepo.getAllOrders());
		orderView.setItems(orderList);
	}

}
