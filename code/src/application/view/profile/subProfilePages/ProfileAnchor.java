package application.view.profile.subProfilePages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.viewModel.profile.subProfilePages.ProfileAnchorViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ProfileAnchor {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextArea aboutMeTextArea;

	@FXML
	private Text dislikedGamesTextField;

	@FXML
	private TextField genresFavortiteGameTextField;

	@FXML
	private Text likedGamesTextField;

	@FXML
	private AnchorPane profileAnchor;

	@FXML
	private TextField titleFavoriteGameTextField;

	private ProfileAnchorViewModel profileAnchorViewModel;

	/**
	 * Instantiates a new profile anchor.
	 */
	public ProfileAnchor() {
		this.profileAnchorViewModel = new ProfileAnchorViewModel();
	}

	@FXML
	void initialize() {
		this.validateFXMLComponets();
		this.bindToViewModel();
		this.profileAnchorViewModel.setUpAboutMeDescription();
		this.profileAnchorViewModel.setUpGameLikedAndDislikeCounters();

	}

	private void bindToViewModel() {
		this.aboutMeTextArea.textProperty().bindBidirectional(this.profileAnchorViewModel.getAboutMeProperty());
		this.likedGamesTextField.textProperty().bindBidirectional(this.profileAnchorViewModel.getLikedGamesProperty());
		this.dislikedGamesTextField.textProperty()
				.bindBidirectional(this.profileAnchorViewModel.getDislikedGamesProperty());
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

		}
	}

	private void validateFXMLComponets() {
		assert this.aboutMeTextArea != null
				: "fx:id=\"aboutMeTextArea\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
		assert this.dislikedGamesTextField != null
				: "fx:id=\"dislikedGamesTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
		assert this.genresFavortiteGameTextField != null
				: "fx:id=\"genresFavortiteGameTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
		assert this.likedGamesTextField != null
				: "fx:id=\"likedGamesTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
		assert this.profileAnchor != null
				: "fx:id=\"profileAnchor\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
		assert this.titleFavoriteGameTextField != null
				: "fx:id=\"titleFavoriteGameTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
	}

}
