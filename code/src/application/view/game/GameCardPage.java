package application.view.game;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.game.Game;
import application.model.profile.ActiveUser;
import application.viewModel.game.GameCardPageViewModel;
import application.viewModel.mystiverse.MystiverseViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Class GameCardPage.
 * 
 * @author daniel rivera
 * @version sprint 1
 */
public class GameCardPage {

    /** The resources. */
    @FXML
    private ResourceBundle resources;

    /** The location. */
    @FXML
    private URL location;

    /** The description text box. */
    @FXML
    private TextArea descriptionTextBox;

    /** The genres text box. */
    @FXML
    private TextField genresTextBox;

    /** The gui pane. */
    @FXML
    private AnchorPane guiPane;

    /** The image view. */
    @FXML
    private ImageView imageView;

    /** The interested button. */
    @FXML
    private Button interestedButton;

    /** The not interested button. */
    @FXML
    private Button notInterestedButton;

    /** The title text box. */
    @FXML
    private TextField titleTextBox;
    
    /** The game card page viewmodel. */
    private GameCardPageViewModel gameCardPageViewmodel;
    
    /**
     * the game card page constructor
     */
    public GameCardPage() {
    	this.gameCardPageViewmodel = new GameCardPageViewModel();
    }

	/**
     * Initialize.
     */
    @FXML
    void initialize() {
    	this.validateFxml();
    	this.setupButtons();
    	this.setGameInformation(GameCardPageViewModel.getCurrRecommendation());
    }
    
    /**
     * Setup buttons.
     */
    private void setupButtons() {
    	this.interestedButton.setOnAction((event) -> {
    		this.handleInterestedButton();
    	});
    	this.notInterestedButton.setOnAction((event) -> {
    		this.handleNotInterestedButton();
    	});
    }
    
    /**
     * Handle not interested button.
     */
    private void handleNotInterestedButton() {
    	Game currGame = MystiverseViewModel.getRecommendedGames().stream().filter(game -> game.getName().equals(this.titleTextBox.getText())).findFirst().orElseThrow();
    	this.gameCardPageViewmodel.addGameToDislikedLibrary(ActiveUser.getActiveUser(), currGame);
    	Stage stage = (Stage) this.guiPane.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Handle interested button.
     */
    private void handleInterestedButton() {
    	Game currGame = MystiverseViewModel.getRecommendedGames().stream().filter(game -> game.getName().equals(this.titleTextBox.getText())).findFirst().orElseThrow();
    	this.gameCardPageViewmodel.addGameToLikedLibrary(ActiveUser.getActiveUser(), currGame);
    	Stage stage = (Stage) this.guiPane.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Sets the game information.
     *
     * @param game the new game information
     */
    private void setGameInformation(Game game) {
		this.titleTextBox.setText(game.getName());
		this.genresTextBox.setText(game.getGenres().toString());
		this.descriptionTextBox.setText(game.getDescription());
    }
    
    /**
     * Validate fxml.
     */
    private void validateFxml() {
        assert this.descriptionTextBox != null : "fx:id=\"descriptionTextBox\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.genresTextBox != null : "fx:id=\"genresTextBox\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.guiPane != null : "fx:id=\"guiPane\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.interestedButton != null : "fx:id=\"interestedButton\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.notInterestedButton != null : "fx:id=\"notInterestedButton\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.titleTextBox != null : "fx:id=\"titleTextBox\" was not injected: check your FXML file 'GameCardPage.fxml'.";
    }
}