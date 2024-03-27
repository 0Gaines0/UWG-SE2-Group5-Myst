/*
 * 
 */
package application.model.abstract_impl.profile;

import java.util.List;
import java.util.Map;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.abstract_impl.profile.ProfileAttributes;

public abstract class UserProfile {

	/**
	 * Gets the all owned games.
	 *
	 * @return the all owned games
	 */
	public abstract List<Game> getAllOwnedGames();
	
	/**
	 * Sets the all owned games.
	 *
	 * @param ownedGames the new all owned games
	 */
	public abstract void setAllOwnedGames(List<Game> ownedGames);
	
	/**
	 * Gets the all liked games.
	 *
	 * @return the all liked games
	 */
	public abstract List<Game> getAllLikedGames();
	
	/**
	 * Sets the all liked games.
	 *
	 * @param likedGames the new all liked games
	 */
	public abstract void setAllLikedGames(List<Game> likedGames);
	
	
	/**
	 * Gets the all disliked games.
	 *
	 * @return the all disliked games
	 */
	public abstract List<Game> getAllDislikedGames();
	
	/**
	 * Sets the all disliked games.
	 *
	 * @param dislikedGames the new all disliked games
	 */
	public abstract void setAllDislikedGames(List<Game> dislikedGames);
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public abstract String getUsername();
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public abstract void setUsername(String username);
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public abstract String getPassword();
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public abstract void setPassword(String password);
	
	/**
	 * Gets the preferred genres.
	 *
	 * @return the preferred genres
	 */
	public abstract List<Genre> getPreferredGenres();
	
	/**
	 * Sets the preferred genres.
	 *
	 * @param preferredGenres the new preferred genres
	 */
	public abstract void setPreferredGenres(List<Genre> preferredGenres);
	
	/**
	 * Gets the profile attributes.
	 *
	 * @return the profile attributes
	 */
	public abstract ProfileAttributes getProfileAttributes();
	
	/**
	 * Checks if is first time login.
	 *
	 * @return true, if is first time login
	 */
	public abstract boolean isFirstTimeLogin();
	
	/**
	 * Sets the first time login.
	 *
	 * @param firstTimeLogin the new first time login
	 */
	public abstract void setFirstTimeLogin(boolean firstTimeLogin);
	
	/**
	 * Calculate genre percentages.
	 *
	 * @return the map
	 */
	public abstract Map<Genre, Double> calculateGenrePercentages();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
