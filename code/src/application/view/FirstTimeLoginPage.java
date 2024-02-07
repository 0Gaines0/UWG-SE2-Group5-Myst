package application.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.model.game.Game;
import application.model.game.Genre;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FirstTimeLoginPage {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private AnchorPane guiPane;

    @FXML
    private URL location;

    @FXML
    private Button continueButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<Genre> highPreferenceComboBox;

    @FXML
    private ComboBox<Genre> lowPreferenceComboBox;

    @FXML
    private ComboBox<Genre> mediumPreferenceComboBox;
    
    @FXML
    private ListView<Game> ownedGamesListView;

    public FirstTimeLoginPage() {
    	
    }
    
    @FXML
    void initialize() {
    	this.setupButtons();
    	this.setupComboBoxes();
    	this.setupListView();
    	this.fxmlValidCmponents();
    }
    
    private void setupListView() {
    	this.ownedGamesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Game game1 = new Game("Game 1", List.of(Genre.ACTION, Genre.ADVENTURE), 1, "Developer 1", 2020, 5, 100, 10, 20);
        Game game2 = new Game("Game 2", List.of(Genre.SIMULATION, Genre.STRATEGY), 2, "Developer 2", 2019, 8, 150, 5, 25);
        Game game3 = new Game("Game 3", List.of(Genre.RPG, Genre.SPORTS), 3, "Developer 3", 2021, 3, 80, 15, 15);
        Game game4 = new Game("Game 4", List.of(Genre.ACTION, Genre.SIMULATION), 4, "Developer 4", 2018, 11, 120, 8, 30);
        Game game5 = new Game("Game 5", List.of(Genre.ADVENTURE, Genre.RPG), 5, "Developer 5", 2022, 7, 90, 12, 18);
        Game[] games = { game1, game2, game3, game4, game5 };
        this.ownedGamesListView.getItems().addAll(games);
    }
    
    private void setupComboBoxes() {
		this.highPreferenceComboBox.getItems().setAll(Genre.values());
		this.mediumPreferenceComboBox.getItems().setAll(Genre.values());
		this.lowPreferenceComboBox.getItems().setAll(Genre.values());
    }
    
    private void setupButtons() {
    	this.cancelButton.setOnAction((event) -> {
    		var stage = (Stage) this.cancelButton.getScene().getWindow();
    		stage.close();
    	});
    	this.continueButton.setOnAction((event) -> {
    		
    	});
    }

    private void fxmlValidCmponents() {
        assert this.continueButton != null : "fx:id=\"Continue\" was not injected: check your FXML file 'FirstTimeLoginPage.fxml'.";
        assert this.cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'FirstTimeLoginPage.fxml'.";
        assert this.guiPane != null : "fx:id=\"guiPane\" was not injected: check your FXML file 'FirstTimeLoginPage.fxml'.";
        assert this.highPreferenceComboBox != null : "fx:id=\"highPreferenceComboBox\" was not injected: check your FXML file 'FirstTimeLoginPage.fxml'.";
        assert this.lowPreferenceComboBox != null : "fx:id=\"lowPreferenceComboBox\" was not injected: check your FXML file 'FirstTimeLoginPage.fxml'.";
        assert this.mediumPreferenceComboBox != null : "fx:id=\"mediumPreferenceComboBox\" was not injected: check your FXML file 'FirstTimeLoginPage.fxml'.";
        assert this.ownedGamesListView != null : "fx:id=\"ownedGamesListView\" was not injected: check your FXML file 'FirstTimeLoginPage.fxml'.";
    }
}
