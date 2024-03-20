package application.view.mystiverse.subMystiversePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    
    public RecommendationPageAnchor() {
    	this.viewmodel = new RecommendationPageAnchorViewModel();
    }
    
	/**
	 * Open anchor pane.
	 *
	 * @param parent        the parent
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
        assert gameDescTextArea != null : "fx:id=\"gameDescTextArea\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert gameGenreTextBox != null : "fx:id=\"gameGenreTextBox\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert gameImageView != null : "fx:id=\"gameImageView\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert gameTitleTextBox != null : "fx:id=\"gameTitleTextBox\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert interestedButton != null : "fx:id=\"interestedButton\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert notInterestedButton != null : "fx:id=\"notInterestedButton\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert recommendationAnchorPane != null : "fx:id=\"recommendationAnchorPane\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";
        assert skipButton != null : "fx:id=\"skipButton\" was not injected: check your FXML file 'RecommendationPageAnchor.fxml'.";

    }

}
