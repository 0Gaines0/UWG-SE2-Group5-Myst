package application.view.mystiverse.subMystiversePages;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.view.mystiverse.MystiversePage;
import application.viewModel.mystiverse.subMystiversePages.SeedPageAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SeedPageAnchor {

    @FXML
    private ResourceBundle resources;

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
	}
    
	private void bindToViewModel() {
		this.gamesListView.itemsProperty().bindBidirectional(this.viewmodel.getAllGames());
		this.genresListView.itemsProperty().bindBidirectional(this.viewmodel.getAllGenres());

		this.setUpLikedGamesChangeListener();
		this.setUpLikedGenresChangeListener();
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

	private void setUpLikedGamesChangeListener() {
		this.gamesListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.viewmodel.addSelectedGame(newValue);
						if (!this.selectedGamesListView.getItems().contains(newValue)) {
							this.selectedGamesListView.getItems().add(newValue);
						}
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
        assert this.gamesListView != null : "fx:id=\"gamesListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.genresListView != null : "fx:id=\"genresListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.seedPageAnchorPane != null : "fx:id=\"seedPageAnchorPane\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.selectedGamesListView != null : "fx:id=\"selectedGamesListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.selectedGenresListView != null : "fx:id=\"selectedGenresListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
    }

}
