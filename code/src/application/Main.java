package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public static final String WINDOW_TITLE = "Myst";
	public static final String LOGIN_WINDOW = "view/login/LoginPage.fxml";
	public static final String CREATE_ACCOUNT_WINDOW = "../login/CreateAccountPage.fxml";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(Main.LOGIN_WINDOW));
			Scene scene = new Scene(parent);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
