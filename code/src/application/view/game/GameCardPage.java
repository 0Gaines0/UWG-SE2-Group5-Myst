package application.view.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameCardPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea descriptionTextBox;

    @FXML
    private TextField genresTextBox;

    @FXML
    private AnchorPane guiPane;

    @FXML
    private ImageView imageView;

    @FXML
    private Button interestedButton;

    @FXML
    private Button notInterestedButton;

    @FXML
    private TextField titleTextBox;

    @FXML
    void initialize() {
        assert this.descriptionTextBox != null : "fx:id=\"descriptionTextBox\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.genresTextBox != null : "fx:id=\"genresTextBox\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.guiPane != null : "fx:id=\"guiPane\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.interestedButton != null : "fx:id=\"interestedButton\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.notInterestedButton != null : "fx:id=\"notInterestedButton\" was not injected: check your FXML file 'GameCardPage.fxml'.";
        assert this.titleTextBox != null : "fx:id=\"titleTextBox\" was not injected: check your FXML file 'GameCardPage.fxml'.";
    }
}