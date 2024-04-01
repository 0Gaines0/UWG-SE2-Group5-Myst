package application;

import java.util.List;

import application.fileIO.GameLibraryIO;
import application.model.local_impl.game.Game;
import application.model.server_impl.game.GameLibraryManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public static final String WINDOW_TITLE = "Myst";
	public static final String LOGIN_WINDOW = "view/login/LoginPage.fxml";
	public static final String CREATE_ACCOUNT_WINDOW = "../login/CreateAccountPage.fxml";
	public static final String USER_PROFILE_WINDOW = "../profile/UserProfilePage.fxml";
	public static final String PROFILE_ANCHOR_PATH_ONE = "../profile/subProfilePages/ProfileAnchor.fxml";
	public static final String PROFILE_ANCHOR_PATH_TWO = "../subProfilePages/ProfileAnchor.fxml";
	public static final String EDIT_PROFILE_ANCHOR = "../subProfilePages/EditProfileAnchor.fxml";
	public static final String USER_GAME_LIBRARY_WINDOW = "../UserGameLibraryPage/UserGameLibraryPage.fxml";
	public static final String MYSTIVERSE_PAGE = "../mystiverse/MystiversePage.fxml";
	public static final String MYSTIVERSE_PAGE_RECOMMENDATIONS_TAB = "../subMystiversePages/RecommendationPageAnchor.fxml";
	public static final String MYSTIVERSE_PAGE_SEED_TAB = "../subMystiversePages/SeedPageAnchor.fxml";
	public static final String MYSTIVERSE_PAGE_ALL_GAMES_TAB_ONE = "../mystiverse/subMystiversePages/AllGamesPageAnchor.fxml";
	public static final String MYSTIVERSE_PAGE_ALL_GAMES_TAB_TWO = "../subMystiversePages/AllGamesPageAnchor.fxml";
	public static final String EDIT_PREFERENCES_ANCHOR = "../subProfilePages/EditPreferencesAnchor.fxml";
	public static final String PROFILE_SETTINGS_ANCHOR = "../subProfilePages/SettingsProfileAnchor.fxml";
	public static final String GAME_CARD_PAGE = "../game/GameCardPage.fxml";
	public static final String PREFERENCE_PAGE_WINDOW = "../login/PreferencePage.fxml";
	
	private static List<Game> allGames;

	@Override
	public void start(Stage primaryStage) {
		Main.allGames = GameLibraryManager.fetchAndParseGameLibrary().getGames();
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

	/**
	 * Gets the games.
	 *
	 * @return the games
	 */
	public static List<Game> getGames() {
		return Main.allGames;
	}
	
	/**
	 * Sets the games.
	 *
	 * @param games the new games
	 */
	public static void setGames(List<Game> games) {
		Main.allGames = games;
	}
}