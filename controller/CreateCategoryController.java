package at.sipovsven.GetIt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import at.sipovsven.GetIt.model.Category;
import at.sipovsven.GetIt.repository.CategoryRepositoryJPA;
import at.sipovsven.GetIt.service.NotificationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateCategoryController {

	@FXML
	private Pane backGround;

	@FXML
	private JFXButton createCategoryButton;

	@FXML
	private JFXButton cancelButton;

	@FXML
	private JFXTextField categoryTxtField;

	@FXML
	private TextField categoryNameTxtField;

	@FXML
	private TextField describtionTxtField;

	CategoryRepositoryJPA cateRepo = new CategoryRepositoryJPA();
	NotificationService notification = new NotificationService();

	@FXML
	void cancelWindow(ActionEvent event) {

	}

	@FXML
	void createCategory(ActionEvent event) {
		try {
			Category category = new Category(categoryNameTxtField.getText());
			cateRepo.addCategory(category);
			notification.showNotificationOnPane("Success", "Category was created!", backGround);
		} catch (Exception e) {
			notification.showErrorMessageOnPane("Error", "Category wasnt created please check for mistakes",
					backGround);
		}

	}

}
