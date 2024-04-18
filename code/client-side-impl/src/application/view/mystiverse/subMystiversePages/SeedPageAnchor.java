package application.view.mystiverse.subMystiversePages;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.viewModel.mystiverse.subMystiversePages.SeedPageAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SeedPageAnchor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField gamesSearchBar;
    
    @FXML
    private TextField genresSearchBar;
    
    @FXML
    private URL location;

    @FXML
    private Button beginRecommendationsButton;

    @FXML
    private ListView<Game> gamesListView;

    @FXML
    private ListView<Genre> genresListView;

    @FXML
    private AnchorPane seedPageAnchorPane;

    @FXML
    private ListView<Game> selectedGamesListView;

    @FXML
    private ListView<Genre> selectedGenresListView;

    private SeedPageAnchorViewModel viewmodel;
    
    /**
     * Instantiates a new seed page anchor.
     */
    public SeedPageAnchor() {
    	this.viewmodel = new SeedPageAnchorViewModel();
    }

	@FXML
    void initialize() {
    	this.validateFxml();
    	this.bindToViewModel();
    	this.setupButton();
    	this.setupListView();
    	this.setupSearchBars();
    }
	
	private void setupSearchBars() {
    	this.gamesSearchBar.setOnKeyReleased((event) -> {
    		this.gamesListView.getItems().setAll(this.viewmodel.filterGamesSearch(this.gamesSearchBar.getText().toLowerCase(), this.gamesListView.getItems()));
    	});
    	this.genresSearchBar.setOnKeyReleased((event) -> {
    		this.genresListView.getItems().setAll(this.viewmodel.filterGenresSearch(this.genresSearchBar.getText().toLowerCase(), this.genresListView.getItems()));
    	});
	}
    
	private void setupButton() {
	    this.beginRecommendationsButton.setOnAction((event) -> {
	        var recommendations = this.viewmodel.generateSeededRecommendations(
	                this.selectedGamesListView.getSelectionModel().getSelectedItems(), 
	                this.selectedGenresListView.getSelectionModel().getSelectedItems());
	        this.openRecommendationPage(recommendations);
	    });
	}

	private void openRecommendationPage(List<Game> recommendations) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.MYSTIVERSE_PAGE_RECOMMENDATIONS_TAB));
	        AnchorPane recommendationPageAnchorPane = loader.load();
	        RecommendationPageAnchor recommendationAnchor = loader.getController();
	        recommendationAnchor.setRecommendations(recommendations);
	        BorderPane parentBorderPane = (BorderPane) this.seedPageAnchorPane.getParent();
	        parentBorderPane.setCenter(recommendationPageAnchorPane);
	    } catch (IOException error) {
	        error.printStackTrace();
	    }
	}
    
	private void setupListView() {
		this.gamesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.genresListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.viewmodel.setUpAllGamesList();
		this.viewmodel.setUpAllGenresList();
		this.viewmodel.setUpSelectedGamesList();
		this.viewmodel.setUpSelectedGenresList();
	}
    
	private void bindToViewModel() {
		this.gamesListView.itemsProperty().bindBidirectional(this.viewmodel.getAllGames());
		this.genresListView.itemsProperty().bindBidirectional(this.viewmodel.getAllGenres());
		this.selectedGamesListView.itemsProperty().bindBidirectional(this.viewmodel.getSelectedSeedGames());
		this.selectedGenresListView.itemsProperty().bindBidirectional(this.viewmodel.getSelectedSeedGenres());
		this.setUpLikedGamesChangeListener();
		this.setUpLikedGenresChangeListener();
		this.setUpSelectedGamesChangeListener();
		this.setUpSelectedGenresChangeListener();
	}

	private void setUpLikedGamesChangeListener() {
		this.gamesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.viewmodel.addSelectedGame(newValue);
				if (!this.selectedGamesListView.getItems().contains(newValue)) {
					this.selectedGamesListView.getItems().add(newValue);
				}
			}
		});
	}
	
	private void setUpSelectedGamesChangeListener() {
		this.selectedGamesListView.setOnMouseClicked(event -> {
			var item = this.selectedGamesListView.getSelectionModel().getSelectedItem();
			if (item != null) {
				this.viewmodel.removeSelectedGame(item);
			}
		});
	}
	
	private void setUpLikedGenresChangeListener() {
		this.genresListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.viewmodel.addSelectedGenre(newValue);
				if (!this.selectedGenresListView.getItems().contains(newValue)) {
					this.selectedGenresListView.getItems().add(newValue);
				}
			}
		});
	}
	
	private void setUpSelectedGenresChangeListener() {
		this.selectedGenresListView.setOnMouseClicked(event -> {
			var item = this.selectedGenresListView.getSelectionModel().getSelectedItem();
			if (item != null) {
				this.viewmodel.removeSelectedGenre(item);
			}
		});
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
    
    private void validateFxml() {
        assert this.beginRecommendationsButton != null : "fx:id=\"beginRecommendationsButton\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.gamesSearchBar != null : "fx:id=\"gamesSearchBar\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.genresSearchBar != null : "fx:id=\"genresSearchBar\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.gamesListView != null : "fx:id=\"gamesListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.genresListView != null : "fx:id=\"genresListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.seedPageAnchorPane != null : "fx:id=\"seedPageAnchorPane\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.selectedGamesListView != null : "fx:id=\"selectedGamesListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.selectedGenresListView != null : "fx:id=\"selectedGenresListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
    }

}
