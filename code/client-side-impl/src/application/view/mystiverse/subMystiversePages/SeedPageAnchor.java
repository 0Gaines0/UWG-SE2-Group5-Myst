package application.view.mystiverse.subMystiversePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.viewModel.mystiverse.subMystiversePages.SeedPageAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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
    private ComboBox<Game> gamesComboBox;

    @FXML
    private ComboBox<Genre> genresComboBox;

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
        assert this.beginRecommendationsButton != null : "fx:id=\"beginRecommendationsButton\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.gamesComboBox != null : "fx:id=\"gamesComboBox\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.genresComboBox != null : "fx:id=\"genresComboBox\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.seedPageAnchorPane != null : "fx:id=\"seedPageAnchorPane\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.selectedGamesListView != null : "fx:id=\"selectedGamesListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";
        assert this.selectedGenresListView != null : "fx:id=\"selectedGenresListView\" was not injected: check your FXML file 'SeedPageAnchor.fxml'.";

    }

}
