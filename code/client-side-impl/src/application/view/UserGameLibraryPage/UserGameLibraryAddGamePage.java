package application.view.UserGameLibraryPage;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.viewModel.mystiverse.subMystiversePages.AllGamesPageAnchorViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UserGameLibraryAddGamePage {

    @FXML
    private ListView<Game> gamesListView;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private ContextMenu addGameToLibraryContextMenu;
    
    @FXML
    private MenuItem addToLikedMenuItem;
    
    @FXML
    private MenuItem addToDislikedMenuItem;
    
    @FXML
    private MenuItem addToOwnedMenuItem;

    @FXML
    private ComboBox<Genre> genresComboBox;

    @FXML
    private TextField searchTextField;
    
    private AllGamesPageAnchorViewModel viewModel;
    
    /**
     * Creates a new UserGameLibraryAddGamePage
     * 
     */
    public UserGameLibraryAddGamePage() {
    	this.viewModel = new AllGamesPageAnchorViewModel();
    }

    @FXML
    void initialize() {
    	this.validateFXML();
    	this.setupSearchbar();
    	this.setupGenresComboBox();
    	this.setupListView();
    	this.setupContextMenu();
    }
    
    private void setupSearchbar() {
    	this.searchTextField.setOnKeyReleased((event) -> {
    		this.gamesListView.getItems().setAll(this.viewModel.filterOnSearch(this.searchTextField.getText(), Main.getGames()));
    	});
    }
    
    private void setupGenresComboBox() {
		this.genresComboBox.getItems().setAll(Genre.values());
		this.genresComboBox.setOnAction((event) -> {
			var selectedGenre = this.genresComboBox.getValue();
			this.gamesListView.getItems().setAll(this.viewModel.searchForGenre(selectedGenre, Main.getGames()));
		});
    }
    
    private void setupListView() {
    	this.gamesListView.getItems().setAll(Main.getGames());
    }
    
    private void setupContextMenu() {
    	this.addToLikedMenuItem.setOnAction((event) -> {
    		this.viewModel.addGameToInterestedList(this.gamesListView.getSelectionModel().getSelectedItem());
    	});
    	this.addToDislikedMenuItem.setOnAction((event) -> {
    		this.viewModel.addGameToDislikedList(this.gamesListView.getSelectionModel().getSelectedItem());
    	});
    	this.addToOwnedMenuItem.setOnAction((event) -> {
    		this.viewModel.addGameToOwnedList(this.gamesListView.getSelectionModel().getSelectedItem());
    	});
    }
    
    private void validateFXML() {
    	assert this.addGameToLibraryContextMenu != null : "fx:id=\"addGameToLibraryContextMenu\" was not injected: check your FXML file 'UserGameLibraryAddGamePage.fxml'.";
        assert this.gamesListView != null : "fx:id=\"gamesListView\" was not injected: check your FXML file 'UserGameLibraryAddGamePage.fxml'.";
        assert this.genresComboBox != null : "fx:id=\"genresComboBox\" was not injected: check your FXML file 'UserGameLibraryAddGamePage.fxml'.";
        assert this.searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'UserGameLibraryAddGamePage.fxml'.";
    }

}
