package application.viewModel;

import application.model.game.Game;
import application.model.game.GameLibrary;
import application.model.game.Genre;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * the first time login page view model
 * 
 * @author daniel rivera 
 * @version Sprint 1
 */
public class FirstTimeLoginPageViewModel {

	private ObjectProperty<Genre> highPriorityGenre;
	private ObjectProperty<Genre> mediumPriorityGenre;
	private ObjectProperty<Genre> lowPriorityGenre;
	private ListProperty<Game> ownedGames;
	private GameLibrary gameLibrary;
	
	/**
	 * the first time login page view model
	 */
	public FirstTimeLoginPageViewModel() {
		this.highPriorityGenre = new SimpleObjectProperty<Genre>();
		this.mediumPriorityGenre = new SimpleObjectProperty<Genre>();
		this.lowPriorityGenre = new SimpleObjectProperty<Genre>();
		this.ownedGames = new SimpleListProperty<Game>();
		this.gameLibrary = new GameLibrary();
	}

	/**
	 * gets the game library
	 * 
	 * @return the game library
	 */
	public GameLibrary getGameLibrary() {
		return this.gameLibrary;
	}

    /**
     * sets the game library 
     * @param gameLibrary
     */
	public void setGameLibrary(GameLibrary gameLibrary) {
		this.gameLibrary = gameLibrary;
	}

	/**
	 * gets the medium priority genre
	 * 
	 * @return the medium priority genre
	 */
	public ObjectProperty<Genre> getHighPriorityGenre() {
		return this.highPriorityGenre;
	}

	/**
	 * gets the medium priority genre
	 * 
	 * @return the medium priority genre
	 */
	public ObjectProperty<Genre> getMediumPriorityGenre() {
		return this.mediumPriorityGenre;
	}

	/**
	 * gets the low priority genre
	 * 
	 * @return the low priority genre
	 */
	public ObjectProperty<Genre> getLowPriorityGenre() {
		return this.lowPriorityGenre;
	}

	/**
	 * gets the owned games list property
	 * 
	 * @return the owned games list property
	 */
	public ListProperty<Game> getOwnedGames() {
		return this.ownedGames;
	}
	
}
