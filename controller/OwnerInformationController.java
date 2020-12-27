package at.sipovsven.GetIt.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import at.sipovsven.GetIt.model.Owner;
import at.sipovsven.GetIt.repository.OwnerRepositoryJPA;
import at.sipovsven.GetIt.service.NotificationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OwnerInformationController {

    @FXML
    private AnchorPane backGround;

    @FXML
    private ImageView imgLogo;

    @FXML
    private JFXButton saveOwnerButton;


    @FXML
    private TextField companyNameTxtField;

    @FXML
    private TextField ownerFirstNameTxtField;

    @FXML
    private TextField ownerLastNameTxtField;

    @FXML
    private TextField ownerEmailTxtField;

    @FXML
    private TextField ownerUidTxtField;

    @FXML
    private TextField ownerPhoneTxtField;

    @FXML
    private TextField ownerBankCodeTxtField;

    @FXML
    private TextField ownerBankNameTxtField;

    @FXML
    private TextField ownerAccountNumTxtField;

    @FXML
    private TextField ownerBicTxtField;

    @FXML
    private TextField ownerCompanyBookTxtField;

    @FXML
    private TextField ownerAddressTxtField;

    OwnerRepositoryJPA ownerRepo = new OwnerRepositoryJPA();
    NotificationService notification = new NotificationService();
    

    @FXML
    void clearMouse(MouseEvent event) {
	this.backGround.requestFocus();
    }

    @FXML
    void saveOwnerInformation(ActionEvent event) throws IOException {
    	try {
			
		
    	Owner owner = new Owner(companyNameTxtField.getText(),ownerFirstNameTxtField.getText(),ownerLastNameTxtField.getText(),ownerAddressTxtField.getText(),
			ownerEmailTxtField.getText(), ownerPhoneTxtField.getText(), ownerUidTxtField.getText(), ownerBankNameTxtField.getText(),ownerAccountNumTxtField.getText(),
			ownerBicTxtField.getText(),ownerBankCodeTxtField.getText(),ownerCompanyBookTxtField.getText());
    	notification.showNotification("Success", "Your Account information has been updated", backGround);
    	ownerRepo.addOwner(owner);
    	} catch (Exception e) {
			// TODO: handle exception
    		notification.showErrorMessage("Error", "Please check if a field is empty!", backGround);
		}
    }

}
