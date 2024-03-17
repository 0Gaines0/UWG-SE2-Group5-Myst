package application.model.server_impl.profile;

import java.util.List;

import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ProfileAttributes;

public class UserProfile extends application.model.abstract_impl.profile.UserProfile {

	@Override
	public List<Game> getAllOwnedGames() {
		return null;
	}

	@Override
	public void setAllOwnedGames(List<Game> ownedGames) {
		
	}

	@Override
	public List<Game> getAllLikedGames() {
		return null;
	}

	@Override
	public void setAllLikedGames(List<Game> likedGames) {		
	}

	@Override
	public List<Game> getAllDislikedGames() {
		return null;
	}

	@Override
	public void setAllDislikedGames(List<Game> dislikedGames) {		
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public void setUsername(String username) {		
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public void setPassword(String password) {
		
	}

	@Override
	public List<Genre> getPreferredGenres() {
		return null;
	}

	@Override
	public void setPreferredGenres(List<Genre> preferredGenres) {
		
	}

	@Override
	public ProfileAttributes getProfileAttributes() {
		return null;
	}

	@Override
	public boolean isFirstTimeLogin() {
		return false;
	}

	@Override
	public void setFirstTimeLogin(boolean firstTimeLogin) {
		
	}

}
