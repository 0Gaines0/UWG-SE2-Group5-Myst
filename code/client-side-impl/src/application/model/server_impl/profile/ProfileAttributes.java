package application.model.server_impl.profile;

import org.json.JSONException;
import org.json.JSONObject;

import application.model.local_impl.game.Game;
import application.model.server_impl.Server;
import application.model.server_impl.ServerConstants;

public class ProfileAttributes extends application.model.abstract_impl.profile.ProfileAttributes {

	private static final String DESCRIPTION_MUST_BE_VALID = "description inputted must not be null or empty";
	private static final String IMAGE_PATH_MUST_BE_VALID = "image path inputted must not be null or empty";
	
	private String aboutMeDescription;
	private Game favoriteGame;
	private String userProfilePicturePath;

	/**
	 * Instantiates a new profile attributes.
	 */
	public ProfileAttributes() {
		this.aboutMeDescription = "";
		this.userProfilePicturePath = "";
	}
	
	/**
	 * Instantiates a new profile attributes.
	 *
	 * @param description the description
	 * @param imagePath   the image path
	 */
	public ProfileAttributes(String description, String imagePath) {
		if (description == null) {
			throw new NullPointerException(DESCRIPTION_MUST_BE_VALID);
		} else if (description.isBlank()) {
			throw new IllegalArgumentException(DESCRIPTION_MUST_BE_VALID);
		} else if (imagePath == null) {
			throw new NullPointerException(IMAGE_PATH_MUST_BE_VALID);
		} else if (imagePath.isBlank()) {
			throw new IllegalArgumentException(IMAGE_PATH_MUST_BE_VALID);
		}
		this.aboutMeDescription = description;
		this.userProfilePicturePath = imagePath;

	}

	@Override
	public String getAboutMeDescription() {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();
		var description = "";
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_ABOUT_ME_DESCRIPTION);
			json.put(ServerConstants.KEY_USERNAME, username);

			var response = Server.sendRequest(json.toString());
			var responseJson = new JSONObject(response);
			if (responseJson.getBoolean(ServerConstants.KEY_SUCCESS)) {
				description = responseJson.get(ServerConstants.VALUE_DESCRIPTION).toString();
			}

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return description;

	}

	@Override
	public void setAboutMeDescription(String aboutMeDescription) {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();
		
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_ABOUT_ME_DESCRIPTION);
			json.put(ServerConstants.KEY_USERNAME, username);
			json.put(ServerConstants.VALUE_DESCRIPTION, aboutMeDescription);

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public Game getFavoriteGame() {
		return null;
	}

	@Override
	public void setFavoriteGame(Game favoriteGame) {

	}

	@Override
	public String getUserProfilePicturePath() {
		var path = "";
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_USER_PROFILE_PICTURE_PATH);
			json.put(ServerConstants.KEY_USERNAME, username);

			var response = Server.sendRequest(json.toString());
			var responseJson = new JSONObject(response);
			if (responseJson.getBoolean(ServerConstants.KEY_SUCCESS)) {
				path = responseJson.get(ServerConstants.VALUE_PATH).toString();
			}

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		return path;
	}

	@Override
	public void setUserProfilePicturePath(String userProfilePicture) {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();
		
		try {
			json.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_USER_PROFILE_PICTURE_PATH);
			json.put(ServerConstants.KEY_USERNAME, username);
			json.put(ServerConstants.VALUE_PATH, userProfilePicture);

			Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
