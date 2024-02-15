package application.view.UserGameLibraryPage;

import application.viewModel.UserGameLibrary.UserGameLibraryViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class UserGameLibraryPage {
	
	@FXML
	private HBox libraryHBox;
	
	@FXML
	private HBox mystiverseHBox;
	
	@FXML
	private HBox profileHBox;
	
	@FXML
	private ListView myGamesListView;
	
	@FXML
	private TextArea gameInfoTextArea;
	
	@FXML
	private TextArea communityTextArea;
	
	
	private UserGameLibraryViewModel viewModel;
	
	
	public UserGameLibraryPage() {
		this.viewModel = new UserGameLibraryViewModel();
	}
	
	@FXML
	public void Initialize() {
		this.bindToViewModel();
		this.setupListView();
	}

	private void bindToViewModel() {
		this.myGamesListView.itemsProperty().bindBidirectional(this.viewModel.getOwnedGames());
		
	}


	private void setupListView() {
		this.myGamesListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//still needs to connect to some form of games had some manually entered or something
	}
}