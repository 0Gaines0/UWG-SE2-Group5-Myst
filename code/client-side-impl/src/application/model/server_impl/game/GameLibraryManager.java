package application.model.server_impl.game;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.fileIO.GameLibraryIO;
import application.model.local_impl.game.GameLibrary;
import application.model.server_impl.Server;
import application.model.server_impl.ServerConstants;

public class GameLibraryManager {

	/**
	 * Fetch and parse game library.
	 *
	 * @return the game library
	 */
	public static GameLibrary fetchAndParseGameLibrary() {
		var gameLibrary = new GameLibrary();
		try {
			JSONObject requestJson = new JSONObject();
			requestJson.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_GAME_LIBRARY);

			String response = Server.sendRequest(requestJson.toString());
			JSONObject jsonResponse = new JSONObject(response);

			if (jsonResponse.getBoolean(ServerConstants.KEY_SUCCESS)) {
				JSONArray gamesArray = jsonResponse.getJSONArray(ServerConstants.KEY_GAMES);
				gameLibrary = GameLibraryIO.parseGamesFromJson(gamesArray);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Error fetching or parsing game library: " + e.getMessage());
		}
		return gameLibrary;
	}
	
	/**
	 * Fetches comments for a specific game from the server using its game ID.
	 *
	 * This method constructs a JSON request to retrieve comments associated with a game ID,
	 * sends the request to the server, and parses the response. If successful, it returns
	 * the comments concatenated as a single string separated by new lines. If no comments
	 * are found or an error occurs, it handles the situation appropriately.
	 *
	 * @param gameID the ID of the game for which comments are to be fetched.
	 * @return a string containing all comments concatenated, or an error message.
	 * @throws IllegalArgumentException if there is a failure in processing the JSON data.
	 */
	public static String fetchComments(int gameID) {
		JSONObject requestJson = new JSONObject();
		try {
			requestJson.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_GET_COMMENTS);
			requestJson.put(ServerConstants.KEY_GAME_ID, gameID);
			String response = Server.sendRequest(requestJson.toString());
			JSONObject jsonResponse = new JSONObject(response);
			if (jsonResponse.getBoolean(ServerConstants.KEY_SUCCESS)) {
				JSONArray commentsJsonArray = jsonResponse.getJSONArray("comments");
				List<String> comments = new ArrayList<>();
				for (int i = 0; i < commentsJsonArray.length(); i++) {
					comments.add(commentsJsonArray.getString(i));
				}
				return String.join("\n", comments);
			} else {
				return "No comments found.";
			}
		} catch (JSONException e) {
			throw new IllegalArgumentException("Failed to fetch comments: " + e.getMessage());
		}
	}
	
	/**
	 * Sends a new comment to the server associated with a specific game ID.
	 *
	 * This method constructs a JSON request including the game ID and the comment text,
	 * then sends this request to the server to add the comment to the specified game.
	 * It handles JSON-related exceptions that might occur during the process.
	 *
	 * @param gameID the ID of the game to which the comment is to be added.
	 * @param comment the comment text to be sent.
	 * @throws IllegalArgumentException if there is a JSON processing error.
	 */
	public static void sendComment(int gameID, String comment) {
		JSONObject requestJson = new JSONObject();
		try {
			requestJson.put(ServerConstants.KEY_REQUEST_TYPE, ServerConstants.VALUE_SET_COMMENTS);
			requestJson.put(ServerConstants.KEY_GAME_ID, gameID);
			requestJson.put("comment", comment);
			Server.sendRequest(requestJson.toString());
		} catch (JSONException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
