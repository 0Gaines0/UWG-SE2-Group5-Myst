package application.view.login;

import java.net.URL;
import java.util.ResourceBundle;

import application.view.profile.UserProfilePage;
import application.viewModel.login.LoginPageViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The Class LoginPage.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class LoginPage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane borderPane;

	@FXML
	private Button loginButton;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private TextField userNameTextField;

	@FXML
	private Hyperlink createAnAccountLink;

	private LoginPageViewModel loginPageViewModel;
	private CreateAccountPage createAccountCodeBehind;
	private UserProfilePage userProfileCodeBehind;

	/**
	 * Instantiates a new login page.
	 */
	public LoginPage() {
		this.loginPageViewModel = new LoginPageViewModel();
		this.createAccountCodeBehind = new CreateAccountPage();
		this.userProfileCodeBehind = new UserProfilePage();
	}

	@FXML
	void initialize() {
		this.fxmlValidComponents();
		this.setUpLoginButton();
		this.setUpCreateAccountPage();
		this.bindToViewModel();
	}
	
	private void setUpCreateAccountPage() {
		this.createAnAccountLink.setOnAction(((event) -> {
			this.createAccountCodeBehind.openCreateAccountPage();
		}));
	}

	private void setUpLoginButton() {
		this.loginButton.setOnAction(((event) -> {
			if (this.loginFieldsAreNull()) {
				var errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setContentText("Login failed, valid username or password");
				errorPopUp.showAndWait();
			} else {
				var loginResult = this.loginPageViewModel.userLoginIsSuccessful();
				if (loginResult) {
					this.closeWindow();
					var user = this.loginPageViewModel.generateUser();
					this.userProfileCodeBehind.openUserProfilePage(user);
					
				} else {
					var errorPopUp = new Alert(AlertType.ERROR);
					errorPopUp.setContentText("Login failed, incorrect username or password");
					errorPopUp.showAndWait();
				}
			}
			
		}));

	}

	private void closeWindow() {
		var stage = (Stage) this.borderPane.getScene().getWindow();
		stage.close();
	}

	private boolean loginFieldsAreNull() {
		var user = this.userNameTextField.textProperty().getValue() == null;
		var password = this.passwordTextField.textProperty().getValue() == null;
		return user || password;
	}

	private void bindToViewModel() {
		this.userNameTextField.textProperty().bindBidirectional(this.loginPageViewModel.getUsernameProperty());
		this.passwordTextField.textProperty().bindBidirectional(this.loginPageViewModel.getPasswordProperty());
	}

	private void fxmlValidComponents() {
		assert this.borderPane != null
				: "fx:id=\"borderPane\" was not injected: check your FXML file 'LoginPage.fxml'.";
		assert this.loginButton != null
				: "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginPage.fxml'.";
		assert this.passwordTextField != null
				: "fx:id=\"passwordTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
		assert this.userNameTextField != null
				: "fx:id=\"userNameTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
		assert this.createAnAccountLink != null
				: "fx:id=\"createAnAccountLink\" was not injected: check your FXML file 'LoginPage.fxml'.";

	}

}