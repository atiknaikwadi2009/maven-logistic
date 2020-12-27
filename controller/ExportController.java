package at.sipovsven.GetIt.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.service.ExportImportService;
import at.sipovsven.GetIt.service.NotificationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ExportController {

	@FXML
	private AnchorPane backGround;

	@FXML
	private ImageView imgLogo;

	@FXML
	private JFXButton custImportButton;

	@FXML
	private JFXButton custExportButton;

	@FXML
	private JFXButton importProductButton;

	@FXML
	private JFXButton exportProductButton;

	@FXML
	private JFXButton exportInvoiceButton;

	ExportImportService eim = new ExportImportService();
	ExportImportService service = new ExportImportService();
	NotificationService notfication = new NotificationService();

	@FXML
	void clearMouse(MouseEvent event) {

		this.backGround.requestFocus();
	}

	@FXML
	void exportCustomer(ActionEvent event) throws SQLException, IOException {
		Stage stage = new Stage();
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(stage);

		directoryChooser.setInitialDirectory(new File("/Desktop"));

		service.exportCustomers(selectedDirectory.getAbsolutePath());
		notfication.showNotification("Success!", "All Customers exported from Database", backGround);
	}

	@FXML
	void exportInvoice(ActionEvent event) {

	}

	@FXML
	void exportProduct(ActionEvent event) throws SQLException, IOException {

		service.exportProducts();
		notfication.showNotification("Success!", "All Products exported from Database", backGround);
	}

	@FXML
	void importCustomer(ActionEvent event) throws SQLException, IOException {
		Stage stage = (Stage) backGround.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();

		service.importCustomers(fileChooser.showOpenDialog(stage).getAbsolutePath());
		notfication.showNotification("Success!", "All Customers imported into Database", backGround);
	}

	@FXML
	void importProduct(ActionEvent event) throws SQLException, IOException {
		Stage stage = (Stage) backGround.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();

		service.importProducts(fileChooser.showOpenDialog(stage).getAbsolutePath());
		notfication.showNotification("Success!", "All Products imported into Database", backGround);

	}

}
