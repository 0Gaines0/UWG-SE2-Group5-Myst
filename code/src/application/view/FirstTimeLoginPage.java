package application.view;

import java.net.URL;
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

    /**
     * the first time login page constructor
     */
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
    		//will launch to another page or will close modal and begin the recommendations, will need more discussion
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
