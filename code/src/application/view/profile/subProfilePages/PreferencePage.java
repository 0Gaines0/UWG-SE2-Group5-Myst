package application.view.profile.subProfilePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.game.Game;
import application.model.game.Genre;
import application.viewModel.profile.subProfilePages.PreferencePageViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * the preferences page
 * 
 * @author Daniel Rivera
 * @version Sprint 1
 */
public class PreferencePage {

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
    
    private PreferencePageViewModel viewmodel;

    /**
     * the first time login page constructor
     */
    public PreferencePage() {
    	this.viewmodel = new PreferencePageViewModel();
    }
    
    public void openPreferencePAge() {
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
    
    @FXML
    void initialize() {
    	this.fxmlValidCmponents();
    	this.setupButtons();
    	this.setupComboBoxes();
    	this.setupListView();
    	this.bindToViewModel();
    }
    
    private void bindToViewModel() {
    	this.highPreferenceComboBox.valueProperty().bindBidirectional(this.viewmodel.getHighPriorityGenre());
    	this.mediumPreferenceComboBox.valueProperty().bindBidirectional(this.viewmodel.getMediumPriorityGenre());
    	this.lowPreferenceComboBox.valueProperty().bindBidirectional(this.viewmodel.getLowPriorityGenre());
    	this.ownedGamesListView.itemsProperty().bindBidirectional(this.viewmodel.getSelectedLikedGames());
    }
    
    private void setupListView() {
    	this.ownedGamesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	//still needs to connect to some form of games had some manually entered or something
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
    		this.viewmodel.configureNewUserPreferences();
    		var stage = (Stage) this.continueButton.getScene().getWindow();
    		stage.close();
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
