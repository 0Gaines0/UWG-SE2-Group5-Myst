package application.model.server_impl.profile;

import org.json.JSONException;
import org.json.JSONObject;

import application.model.local_impl.game.Game;
import application.model.server_impl.Server;

public class ProfileAttributes extends application.model.abstract_impl.profile.ProfileAttributes {

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

	@Override
	public String getAboutMeDescription() {
		var json = new JSONObject();
		var username = ActiveUser.getActiveUser().getUsername();
		var description = "";
		try {
			json.put("request_type", "get_about_me_description");
			json.put("username", username);

			var response = Server.sendRequest(json.toString());

		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return description;

	}

	@Override
	public void setAboutMeDescription(String aboutMeDescription) {

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
		return null;
	}

	@Override
	public void setUserProfilePicturePath(String userProfilePicture) {

	}

}
