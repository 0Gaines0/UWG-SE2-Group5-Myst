package application.view.profile.subProfilePages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    void initialize() {
        this.validateFXMLComponets();

    }

	private void validateFXMLComponets() {
		assert this.aboutMeTextArea != null : "fx:id=\"aboutMeTextArea\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
        assert this.dislikedGamesTextField != null : "fx:id=\"dislikedGamesTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
        assert this.genresFavortiteGameTextField != null : "fx:id=\"genresFavortiteGameTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
        assert this.likedGamesTextField != null : "fx:id=\"likedGamesTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
        assert this.profileAnchor != null : "fx:id=\"profileAnchor\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
        assert this.titleFavoriteGameTextField != null : "fx:id=\"titleFavoriteGameTextField\" was not injected: check your FXML file 'ProfileAnchor.fxml'.";
	}

}
