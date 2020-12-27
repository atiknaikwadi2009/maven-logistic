package at.sipovsven.GetIt.controller;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.application.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController extends App {

	@FXML
	private AnchorPane backGround;

	@FXML
	private JFXButton inventoryButton;

	@FXML
	private MenuButton storageButton;

	@FXML
	private MenuButton orderMenu;

	@FXML
	private MenuItem orderButton;

	@FXML
	private MenuItem supplierMenu;

	@FXML
	private MenuItem statisticsMenu;

	@FXML
	private MenuItem myOrderButton;

	@FXML
	private ImageView imgLogo;

	@FXML
	private MenuButton settingButton;

	@FXML
	private JFXButton accountButton;

	@FXML
	private MenuItem aboutDropDown;

	@FXML
	private JFXButton customerButton;

	@FXML
	private JFXButton exportButton;

	@FXML
	private JFXButton cssButton;

	Sceneswitcher sceneswitcher = new Sceneswitcher();
	Boolean pressed = true;

	public EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {

		@Override
		public void handle(final ActionEvent event) {

			Scene s = cssButton.getScene();
			if (pressed == true) {
				s.getStylesheets().remove(0);
				s.getStylesheets().add(getClass().getResource("/css/darktheme.css").toExternalForm());
				transferMessage(pressed);
			} else {
				s.getStylesheets().remove(0);
				s.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
				transferMessage(pressed);
			}
		}

	};

	@FXML
	void initialize() {
		// TODO Auto-generated method stub

	}

	@FXML
	void buttonPressed(ActionEvent event) {

		if (pressed) {
			myHandler.handle(event);
			pressed = false;

		} else {

			myHandler.handle(event);
			pressed = true;
		}

		System.out.println(pressed);

	}

	@FXML
	void clearMouse(MouseEvent event) {
		this.backGround.requestFocus();
		this.backGround.toBack();
	}



	@FXML
	void openNewCategoryWindow(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("CreateNewCategory", "New Category", pressed);
	}

	@FXML
	void openInventory(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("Product", "Products", pressed);
	}

	@FXML
	void openCustomers(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("Customer", "Customers", pressed);
	}

	@FXML
	void openExportScene(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("ExportScene", "Import and Export", pressed);
	}

	@FXML
	void openMyOrders(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("SupplierOrder", "My Supplies", pressed);
	}

	@FXML
	void openOrders(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("Orders", "Orders", pressed);
	}

	@FXML
	void openAccount(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("Owner", "My Account information", pressed);
	}

	@FXML
	void openSuppliers(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("Supplier", "My Suppliers", pressed);
	}

	@FXML
	void openStatistics(ActionEvent event) throws Exception {
		sceneswitcher.openNewWindow("OrderStatisticsAreaChart", "Statistics", pressed);
	}

	/*
	 * Function used to transfer boolean data to other Stages
	 * The boolean decides which css files the loads in openNewWindow Function
	 * Found in Class "Sceneswitcher"
	 */
	public boolean transferMessage(boolean pressed) {

		return pressed;

	}

}
