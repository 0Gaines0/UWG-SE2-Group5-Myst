package application.model.server_impl.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.fileIO.GameLibraryIO;
import application.model.local_impl.game.Game;
import application.model.local_impl.game.Genre;
import application.model.local_impl.profile.ProfileAttributes;
import application.model.server_impl.Server;

public class UserProfile extends application.model.abstract_impl.profile.UserProfile {
	
	private static final int LIMIT_GENRES = 6;

	private static final String USERNAME_MUST_NOT_BE_NULL_OR_EMPTY = "username must not be null or empty";
	private static final String PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY = "password must not be null or empty";
	private static final String INPUT_LIST_MUST_NOT_BE_NULL = "inputted game list must not be null";

	private String username;
	private String password;
	private boolean firstTimeLogin;

	/**
	 * Instantiates a new user profile.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public UserProfile(String username, String password) {
		if (username == null) {
			throw new NullPointerException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (username.isBlank()) {
			throw new IllegalArgumentException(USERNAME_MUST_NOT_BE_NULL_OR_EMPTY);
		}
		if (password == null) {
			throw new NullPointerException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		} else if (password.isBlank()) {
			throw new IllegalArgumentException(PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY);
		}

		this.username = username;
		this.password = password;
		this.firstTimeLogin = true;
	}

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

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public List<Game> getAllLikedGames() {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		var likedGames = new ArrayList<Game>();

		try {
			json.put("request_type", "get_all_liked_games");
			json.put("username", username);

			var response = Server.sendRequest(json.toString());
			var jsonResponse = new JSONObject(response);
			if (jsonResponse.getBoolean("success")) {
				likedGames = (ArrayList<Game>) GameLibraryIO.parseGamesFromJson(jsonResponse.getJSONArray("games"))
						.getGames();
			}

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return likedGames;
	}

	@Override
	public void setAllLikedGames(List<Game> likedGames) {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		try {
			json.put("request_type", "set_all_liked_games");
			json.put("username", username);
			var gamesArray = new JSONArray(likedGames);
			json.put("games", gamesArray);

			Server.sendRequest(json.toString());

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
		return this.username;
	}

	@Override
	public void setUsername(String username) {
	}

	@Override
	public String getPassword() {
		return this.password;
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
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();
		List<String> genreNames = new ArrayList<>();
		for (var genre : preferredGenres) {
			genreNames.add(genre.toString());
		}
		try {
			json.put("request_type", "set_preferred_genres");
			json.put("username", username);
			var genreArray = new JSONArray(genreNames);
			json.put("genres", genreArray);

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public ProfileAttributes getProfileAttributes() {
		return null;
	}

	@Override
	public boolean isFirstTimeLogin() {
		return this.firstTimeLogin;
	}

	@Override
	public void setFirstTimeLogin(boolean firstTimeLogin) {

	}

	@Override
	public Map<Genre, Double> calculateGenrePercentages() {
		Map<Genre, Integer> genreCounts = new HashMap<>();
		int totalGenres = 0;

		for (Game game : this.getAllLikedGames()) {
			for (Genre genre : game.getGenres()) {
				genreCounts.put(genre, genreCounts.getOrDefault(genre, 0) + 1);
				totalGenres++;
			}
		}

		for (Game game : this.getAllOwnedGames()) {
			for (Genre genre : game.getGenres()) {
				genreCounts.put(genre, genreCounts.getOrDefault(genre, 0) + 1);
				totalGenres++;
			}
		}

		Map<Genre, Double> genrePercentages = new HashMap<>();
		for (Map.Entry<Genre, Integer> entry : genreCounts.entrySet()) {
			double percentage = 100.0 * entry.getValue() / totalGenres;
			genrePercentages.put(entry.getKey(), percentage);
		}

		genrePercentages = genrePercentages.entrySet().stream()
	            .sorted(Map.Entry.<Genre, Double>comparingByValue().reversed())
	            .limit(LIMIT_GENRES)
	            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return genrePercentages;
	}

}
