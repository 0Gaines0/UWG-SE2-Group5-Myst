package application.model.server_impl.profile;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.fileIO.GameLibraryIO;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ActiveUser;
import application.model.local_impl.profile.ProfileAttributes;
import application.model.server_impl.Server;

public class UserProfile extends application.model.abstract_impl.profile.UserProfile {

	/**
	 * Gets the all owned games.
	 *
	 * @return the all owned games
	 */
	@Override
	public List<Game> getAllOwnedGames() {
		var username = ActiveUser.getActiveUser().getUsername();
		var json = new JSONObject();
		try {
			json.put("request_type", "get_all_owned_games");
			json.put("username", username);

			var response = Server.sendRequest(json.toString());
			var jsonResponse = new JSONObject(response);

			if (jsonResponse.getBoolean("success")) {
				JSONArray gamesArray = jsonResponse.getJSONArray("games");
				return GameLibraryIO.parseGamesFromJson(gamesArray).getGames();
			} else {
				System.err.println("Failed to fetch game library: " + jsonResponse.optString("message"));
			}
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return null;
	}

	@Override
	public void setAllOwnedGames(List<Game> ownedGames) {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		try {
			json.put("request_type", "set_all_owned_games");
			json.put("username", username);
			var gamesArray = new JSONArray(ownedGames);
			json.put("games", gamesArray);

			var response = Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public List<Game> getAllLikedGames() {
		return null;
	}

	@Override
	public void setAllLikedGames(List<Game> likedGames) {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		try {
			json.put("request_type", "set_all_owned_games");
			json.put("username", username);
			var gamesArray = new JSONArray(likedGames);
			json.put("games", gamesArray);

			var response = Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
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
