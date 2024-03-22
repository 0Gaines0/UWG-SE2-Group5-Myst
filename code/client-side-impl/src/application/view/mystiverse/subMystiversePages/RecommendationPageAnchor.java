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
    
    /**
     * Instantiates a new recommendation page anchor.
     */
    public RecommendationPageAnchor() {
    	this.viewmodel = new RecommendationPageAnchorViewModel();
    }
    
    /**
     * Instantiates a new recommendation page anchor.
     *
     * @param recommendations the recommendations
     */
    public RecommendationPageAnchor(List<Game> recommendations) {
    	this.viewmodel = new RecommendationPageAnchorViewModel();
    	this.viewmodel.setRecommendations(recommendations);
    }
    
    /**
     * Sets the recommendations.
     *
     * @param recommendations the new recommendations
     */
    public void setRecommendations(List<Game> recommendations) {
    	this.viewmodel.getRecommendations().clear();
    	this.viewmodel.setRecommendations(recommendations);
    }
    
    private void setupRecommendations() {
    	this.viewmodel.setRecommendations(this.viewmodel.generateRecommendations());
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
    	this.setupButtons();
    	this.viewmodel.setProperties();
    }
    
    private void setupButtons() {
    	this.notInterestedButton.setOnAction((event) -> {
    		this.viewmodel.notInterestedInGame(this.viewmodel.getRecommendations().get(0));
        	this.refreshUI();
    	});
    	this.interestedButton.setOnAction((event) -> {
    		this.viewmodel.interestedInGame(this.viewmodel.getRecommendations().get(0));
        	this.refreshUI();
    	});
    	this.skipButton.setOnAction((event) -> {
    		this.viewmodel.skipGame(this.viewmodel.getRecommendations().get(0));
        	this.refreshUI();
    	});
    }
    
    private void refreshUI() {
        if (!(this.viewmodel.getRecommendations().size() <= 1)) {
            Game currentGame = this.viewmodel.getRecommendations().get(0);
            this.viewmodel.getTitleProperty().set(currentGame.getName());
            this.viewmodel.getDescProperty().set(currentGame.getDescription());
            this.viewmodel.getGenresProperty().set(currentGame.getGenres().toString());
        } else {
        	this.setupRecommendations();
        }
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
