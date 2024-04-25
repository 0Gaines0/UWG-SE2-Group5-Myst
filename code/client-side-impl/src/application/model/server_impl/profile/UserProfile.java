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
import application.model.server_impl.Server;
import application.model.server_impl.ServerConstants;

public class UserProfile extends application.model.abstract_impl.profile.UserProfile {

	private static final int LIMIT_GENRES = 6;

	private static final String USERNAME_MUST_NOT_BE_NULL_OR_EMPTY = "username must not be null or empty";
	private static final String PASSWORD_MUST_NOT_BE_NULL_OR_EMPTY = "password must not be null or empty";

	private String username;
	private String password;

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
	}
	
	/**
	 * Gets the suggested to user games.
	 *
	 * @return the suggested to user games
	 */
	public List<Game> getSuggestedToUserGames() {
		var suggestedGames = new ArrayList<Game>();
		
		var username = ActiveUser.getActiveUser().getUsername();
		var json = new JSONObject();
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_SUGGESTED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);

			var response = Server.sendRequest(json.toString());
			var jsonResponse = new JSONObject(response);

			if (jsonResponse.getBoolean(ServerConstants.KEY_SUCCESS)) {
				JSONArray gamesArray = jsonResponse.getJSONArray(ServerConstants.KEY_GAMES);
				suggestedGames = (ArrayList<Game>) GameLibraryIO.parseGamesFromJson(gamesArray).getGames();
			} 
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		return suggestedGames;
	}
	
	/**
	 * Sets the suggested to user games.
	 *
	 * @param suggestedGames the new suggested to user games
	 */
	public void setSuggestedToUserGames(List<Game> suggestedGames) {
		var username = ActiveUser.getActiveUser().getUsername();
		var json = new JSONObject();
		
		
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_SUGGESTED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);
			var gamesArray = new JSONArray(suggestedGames);
			json.put(ServerConstants.KEY_GAMES, gamesArray);
			
			Server.sendRequest(json.toString());
			
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		
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
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_ALL_OWNED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);

			var response = Server.sendRequest(json.toString());
			var jsonResponse = new JSONObject(response);

			if (jsonResponse.getBoolean(ServerConstants.KEY_SUCCESS)) {
				JSONArray gamesArray = jsonResponse.getJSONArray(ServerConstants.KEY_GAMES);
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
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_ALL_OWNED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);
			var gamesArray = new JSONArray(ownedGames);
			json.put(ServerConstants.KEY_GAMES, gamesArray);

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
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_ALL_LIKED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);

			var response = Server.sendRequest(json.toString());
			var jsonResponse = new JSONObject(response);
			if (jsonResponse.getBoolean(ServerConstants.KEY_SUCCESS)) {
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
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_ALL_LIKED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);
			var gamesArray = new JSONArray(likedGames);
			json.put(ServerConstants.KEY_GAMES, gamesArray);

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public List<Game> getAllDislikedGames() {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		var disliked = new ArrayList<Game>();

		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_ALL_DISLIKED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);

			var response = Server.sendRequest(json.toString());
			var jsonResponse = new JSONObject(response);
			if (jsonResponse.getBoolean(ServerConstants.KEY_SUCCESS)) {
				disliked = (ArrayList<Game>) GameLibraryIO.parseGamesFromJson(jsonResponse.getJSONArray("games"))
						.getGames();
			}

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return disliked;
	}

	@Override
	public void setAllDislikedGames(List<Game> dislikedGames) {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_ALL_DISLIKED_GAMES);
			json.put(ServerConstants.KEY_USERNAME, username);
			var gamesArray = new JSONArray(dislikedGames);
			json.put(ServerConstants.KEY_GAMES, gamesArray);

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
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
		var genres = new ArrayList<Genre>();
		var username = ActiveUser.getActiveUser().getUsername();

		var jsonObject = new JSONObject();

		try {
			jsonObject.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_PREFERRED_GENRES);
			jsonObject.put(ServerConstants.KEY_USERNAME, username);
			
			var response = Server.sendRequest(jsonObject.toString());
			var responseJson = new JSONObject(response);
			if (responseJson.getBoolean(ServerConstants.KEY_SUCCESS)) {
				var genreStrings = responseJson.getJSONArray(ServerConstants.VALUE_GENRES);
				for (int i = 0; i < genreStrings.length(); i++) {
					var genreStr = genreStrings.getString(i);
					var genre = GameLibraryIO.toGenre(genreStr).get();
					genres.add(genre);
				}
			}
						
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());

		}

		return genres;
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
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_PREFERRED_GENRES);
			json.put(ServerConstants.KEY_USERNAME, username);
			var genreArray = new JSONArray(genreNames);
			json.put(ServerConstants.VALUE_GENRES, genreArray);

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@Override
	public ProfileAttributes getProfileAttributes() {
		var descriptionJson = new JSONObject();
		var profilePictureJson = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		var profileAttributes = new ProfileAttributes();
		try {
			descriptionJson.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_ABOUT_ME_DESCRIPTION);
			profilePictureJson.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_USER_PROFILE_PICTURE_PATH);

			descriptionJson.put(ServerConstants.KEY_USERNAME, username);
			profilePictureJson.put(ServerConstants.KEY_USERNAME, username);

			var descriptionResponse = Server.sendRequest(descriptionJson.toString());
			var profilePictureResponse = Server.sendRequest(profilePictureJson.toString());

			var descriptionResponseJson = new JSONObject(descriptionResponse);
			var profilePictureResponseJson = new JSONObject(profilePictureResponse);

			if (descriptionResponseJson.getBoolean(ServerConstants.KEY_SUCCESS) && profilePictureResponseJson.getBoolean("success")) {
				var description = descriptionResponseJson.get(ServerConstants.VALUE_DESCRIPTION).toString();
				var path = profilePictureResponseJson.get(ServerConstants.VALUE_PATH).toString();

				profileAttributes.setAboutMeDescription(description);
				profileAttributes.setUserProfilePicturePath(path);
			}

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return profileAttributes;
	}

	@Override
	public boolean isFirstTimeLogin() {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		var firstTimeLogin = false;

		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_FIRST_TIME_LOGIN);
			json.put(ServerConstants.KEY_USERNAME, username);

			var response = Server.sendRequest(json.toString());
			var jsonResponse = new JSONObject(response);
			if (jsonResponse.getBoolean(ServerConstants.KEY_SUCCESS)) {
				firstTimeLogin = jsonResponse.getBoolean(ServerConstants.VALUE_FIRST_TIME_LOGIN);
			}

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return firstTimeLogin;
	}

	@Override
	public void setFirstTimeLogin(boolean firstTimeLogin) {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();

		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_FIRST_TIME_LOGIN);
			json.put(ServerConstants.KEY_USERNAME, username);
			json.put(ServerConstants.VALUE_FIRST_TIME_LOGIN, firstTimeLogin);

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

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
				.sorted(Map.Entry.<Genre, Double>comparingByValue().reversed()).limit(LIMIT_GENRES)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));

		return genrePercentages;
	}

}
