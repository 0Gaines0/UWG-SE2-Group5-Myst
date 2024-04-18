package application.view.mystiverse.subMystiversePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.viewModel.mystiverse.subMystiversePages.AllGamesPageAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AllGamesPageAnchor {
	
    @FXML
    private Label addGameLabel;

    @FXML
    private ImageView gameImageView;
    
    @FXML
    private Button addGameButton;
    
    @FXML
    private TextArea gameDescTextArea;
	
	@FXML
    private ContextMenu addGameToInterestedMenuItem;
	
    @FXML
    private ImageView gameSelectedImageView;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane allGamesAnchorPane;

    @FXML
    private ListView<Game> allGamesListView;

    @FXML
    private ComboBox<Genre> genresComboBox;

    @FXML
    private TextField searchBar;
    
    private AllGamesPageAnchorViewModel viewmodel;
    
    /**
     * Instantiates a new all games page anchor.
     */
    public AllGamesPageAnchor() {
    	this.viewmodel = new AllGamesPageAnchorViewModel();
    }
    
	/**
	 * Open anchor pane.
	 *
	 * @param parent the parent
	 * @param newAnchorPath the new anchor path
	 */
	public void openAnchorPane(BorderPane parent, String newAnchorPath) {
		try {
			AnchorPane currentAnchor = (AnchorPane) parent.getCenter();
			var loader = new FXMLLoader(getClass().getResource(newAnchorPath));
			AnchorPane newAnchor = loader.load();
			parent.setCenter(newAnchor);
			parent.getChildren().remove(currentAnchor);
		} catch (IOException error) {
			error.getMessage();
		}
	}

    @FXML
    void initialize() {
    	this.validateFxml();
    	this.setupSearchbar();
    	this.setupGenresComboBox();
    	this.setupListView();
    	this.setupContextMenu();
    	this.setupImageView();
    	this.setupGameDescTextBox();
    	this.setupAddButton();
    }
    
    private void setupAddButton() {
    	this.addGameButton.setOnAction((event) -> {
    		var game = this.allGamesListView.getSelectionModel().getSelectedItem();
    		this.viewmodel.addGameToInterestedList(game);
    		if (game != null) {
        		this.addGameLabel.visibleProperty().set(true);
    		}
    	});
    }
    
    private void setupImageView() {
    	this.gameImageView.imageProperty().bindBidirectional(this.viewmodel.getImageProperty());
    }
    
    private void setupSearchbar() {
    	this.searchBar.setOnKeyReleased((event) -> {
    		this.allGamesListView.getItems().setAll(this.viewmodel.filterOnSearch(this.searchBar.getText().toLowerCase(), Main.getGames()));
    	});
    }
    
    private void setupGenresComboBox() {
		this.genresComboBox.getItems().setAll(Genre.values());
		this.genresComboBox.setOnAction((event) -> {
			var selectedGenre = this.genresComboBox.getValue();
			this.allGamesListView.getItems().setAll(this.viewmodel.searchForGenre(selectedGenre, Main.getGames()));
		});
    }
    
    private void setupGameDescTextBox() {
    	this.gameDescTextArea.textProperty().bindBidirectional(this.viewmodel.getGameDescStringProperty());
    }
    
    private void setupListView() {
    	this.allGamesListView.getItems().setAll(Main.getGames());
    	this.allGamesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.viewmodel.setImage(newValue.getGamePhoto());
				this.viewmodel.setGameDesc(newValue.getDescription());
			}
		});
    }
    
    private void setupContextMenu() {
    	this.addGameToInterestedMenuItem.setOnAction((event) -> {
    		var game = this.allGamesListView.getSelectionModel().getSelectedItem();
    		this.viewmodel.addGameToInterestedList(game);
    		if (game != null) {
        		this.addGameLabel.visibleProperty().set(true);
    		}
    	});
    }
    
    private void validateFxml() {
        assert this.addGameButton != null : "fx:id=\"addGameButton\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.addGameLabel != null : "fx:id=\"addGameLabel\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.allGamesAnchorPane != null : "fx:id=\"allGamesAnchorPane\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.allGamesListView != null : "fx:id=\"allGamesListView\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.gameImageView != null : "fx:id=\"gameImageView\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.gameDescTextArea != null : "fx:id=\"gameDescTextAreas\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.genresComboBox != null : "fx:id=\"genresComboBox\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.addGameToInterestedMenuItem != null : "fx:id=\"addGameToInterestedMenuItem\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.gameSelectedImageView != null : "fx:id=\"gameSlectedImageView\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
        assert this.searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'AllGamesPageAnchor.fxml'.";
    }

}
