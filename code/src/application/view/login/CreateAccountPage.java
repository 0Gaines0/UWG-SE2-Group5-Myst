package application.view.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void initialize() {
        assert this.passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
        assert this.reenterPasswordTextField != null : "fx:id=\"reenterPasswordTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";
        assert this.userNameTextField != null : "fx:id=\"userNameTextField\" was not injected: check your FXML file 'CreateAccountPage.fxml'.";

    }

}
