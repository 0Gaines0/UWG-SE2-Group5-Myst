package application.view.mystiverse.subMystiversePages;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.model.local_impl.game.Game;
import application.viewModel.mystiverse.subMystiversePages.RecommendationPageAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RecommendationPageAnchor {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea gameDescTextArea;

    @FXML
    private TextField gameGenreTextBox;

    @FXML
    private ImageView gameImageView;

    @FXML
    private TextField gameTitleTextBox;

    @FXML
    private Button interestedButton;

    @FXML
    private Button notInterestedButton;

    @FXML
    private AnchorPane recommendationAnchorPane;

    @FXML
    private Button skipButton;
    
    private RecommendationPageAnchorViewModel viewmodel;
    private List<Game> recommendations;
    
    /**
     * Instantiates a new recommendation page anchor.
     */
    public RecommendationPageAnchor() {
    	this.viewmodel = new RecommendationPageAnchorViewModel();
    }
    
    private void setupRecommendations() {
    	this.viewmodel.setRecommendations(this.viewmodel.generateRecommendations());
    	this.recommendations = this.viewmodel.getRecommendations();
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
    	this.setupProperties();
    	this.setupRecommendations();
    }
    
    private void setupProperties() {
    	this.gameImageView.imageProperty().bindBidirectional(this.viewmodel.getImageProperty());
    	this.gameTitleTextBox.textProperty().bindBidirectional(this.viewmodel.getTitleProperty());
    	this.gameGenreTextBox.textProperty().bindBidirectional(this.viewmodel.getGenresProperty());
    	this.gameDescTextArea.textProperty().bindBidirectional(this.viewmodel.getDescProperty());
    }

    private void validateFxml() {
        assert this.gameDescTextArea != null : "fx:id=\"gameDescTextArea\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert this.gameGenreTextBox != null : "fx:id=\"gameGenreTextBox\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert this.gameImageView != null : "fx:id=\"gameImageView\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert this.gameTitleTextBox != null : "fx:id=\"gameTitleTextBox\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert this.interestedButton != null : "fx:id=\"interestedButton\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert this.notInterestedButton != null : "fx:id=\"notInterestedButton\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert this.recommendationAnchorPane != null : "fx:id=\"recommendationAnchorPane\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert this.skipButton != null : "fx:id=\"skipButton\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";

    }
}
