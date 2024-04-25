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
                return String.join("\n", comments); // Join comments into a single string separated by new lines
            } else {
                return "No comments found.";
            }
        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to fetch comments: " + e.getMessage());
        }
    }

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
