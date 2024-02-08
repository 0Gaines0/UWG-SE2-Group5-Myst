package application.view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateAccountPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField reenterPasswordTextField;

    @FXML
    private TextField userNameTextField;
    
    @FXML
    private Button cancelButton;

    @FXML
    private Button createAccountButton;

    @FXML
    void initialize() {
        this.fxmlValidComponents();
        

    }
    
    /**
     * Open create account page.
     */
    public void openCreateAccountPage() {
    	var newStage = new Stage();
    	try {
    		var loader = new FXMLLoader(getClass().getResource(Main.CREATE_ACCOUNT_WINDOW));
    		Parent parent = loader.load();
    		var scene = new Scene(parent);
    		newStage.initModality(Modality.APPLICATION_MODAL);
    		newStage.initOwner(((Stage) (parent.getScene().getWindow())));
    		newStage.setTitle(Main.WINDOW_TITLE);
    		newStage.setScene(scene);
    		newStage.show();
    		
    	} catch (IOException error) {
    		error.printStackTrace();
    	}
    }

	private void fxmlValidComponents() {
		assert this.passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
        assert this.reenterPasswordTextField != null : "fx:id=\"reenterPasswordTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
        assert this.userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
        assert this.cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
        assert this.createAccountButton != null : "fx:id=\"createAccountButton\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
	}

}
