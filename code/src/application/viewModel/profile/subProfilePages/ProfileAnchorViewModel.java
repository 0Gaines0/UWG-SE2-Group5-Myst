package application.viewModel.profile.subProfilePages;

import java.util.ArrayList;

import application.model.profile.ActiveUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * The Class ProfileAnchorViewModel.
 * @author Jeffrey Gaines
 * @version Sprint 1
 */
public class ProfileAnchorViewModel {

	private StringProperty aboutMeProperty;
	private StringProperty likedGamesProperty;
	private StringProperty dislikedGamesProperty;
	private StringProperty titleFavoriteGameProperty;
	private StringProperty genresFavoriteGameProperty;
	
	
	/**
	 * Instantiates a new profile anchor view model.
	 */
	public ProfileAnchorViewModel() {
		this.aboutMeProperty = new SimpleStringProperty();
		this.likedGamesProperty = new SimpleStringProperty();
		this.dislikedGamesProperty = new SimpleStringProperty();
		this.titleFavoriteGameProperty = new SimpleStringProperty();
		this.genresFavoriteGameProperty = new SimpleStringProperty();
	}
	
	/**
	 * Sets the up about me description.
	 */
	public void setUpAboutMeDescription() {
		this.aboutMeProperty.setValue(ActiveUser.getActiveUser().getProfileAttributes().getAboutMeDescription());
	}
	
	/**
	 * Sets the up game liked and dislike counters.
	 */
	public void setUpGameLikedAndDislikeCounters() {
		var likedGames = String.valueOf(ActiveUser.getActiveUser().getAllLikedGames().size());
		var dislikedGames = String.valueOf(ActiveUser.getActiveUser().getAllDislikedGames().size());
		this.likedGamesProperty.setValue(likedGames);
		this.dislikedGamesProperty.setValue(dislikedGames);
	}
	
	/**
	 * Sets the up genre pie chart data.
	 *
	 * @return the observable list
	 */
	public ObservableList<PieChart.Data> setUpGenrePieChartData() {
		var userGenreMap = ActiveUser.getActiveUser().calculateGenrePercentages();
		var dataList = new ArrayList<PieChart.Data>();
		for (var genre : userGenreMap.keySet()) {
			var value = userGenreMap.get(genre);
			var data = new PieChart.Data(genre.toString(), value);
			dataList.add(data);
		}
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(dataList);
		
		return pieChartData;
		
		
	}

	/**
	 * Gets the about me property.
	 *
	 * @return the about me property
	 */
	public StringProperty getAboutMeProperty() {
		return this.aboutMeProperty;
	}

	/**
	 * Gets the liked games property.
	 *
	 * @return the liked games property
	 */
	public StringProperty getLikedGamesProperty() {
		return this.likedGamesProperty;
	}

	/**
	 * Gets the disliked games property.
	 *
	 * @return the disliked games property
	 */
	public StringProperty getDislikedGamesProperty() {
		return this.dislikedGamesProperty;
	}
	/**
	 * Gets the title favorite game property.
	 *
	 * @return the title favorite game property
	 */
	public StringProperty getTitleFavoriteGameProperty() {
		return this.titleFavoriteGameProperty;
	}

	/**
	 * Gets the genres favorite game property.
	 *
	 * @return the genres favorite game property
	 */
	public StringProperty getGenresFavoriteGameProperty() {
		return this.genresFavoriteGameProperty;
	}
}
