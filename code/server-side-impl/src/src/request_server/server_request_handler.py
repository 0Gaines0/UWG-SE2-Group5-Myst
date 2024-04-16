'''
Created on Mar 10, 2024

@author: Jeffrey
'''

from src.model.games.gameLibraryIO import GameLibraryIO
from src.model.profile.user_manager import User_Manager
from src.request_server import constants
from src.model.profile.credentials.credential_manager import Credential_Manager
from src.model.profile.active_user import Active_User

class Server_Request_Handler:
    """
    A class responsible for handling and responding to server requests related to user management,
    credential verification, and game library operations.

    This class interfaces with `Credential_Manager`, `User_Manager`, and `GameLibraryIO` to
    perform operations such as adding new credentials, checking for existing usernames, retrieving
    game libraries, and managing user profile data including liked and disliked games, preferred genres,
    and personal information.

    Attributes:
        credential_manager (Credential_Manager): Manages user credentials.
        user_manager (User_Manager): Manages user profiles.
        game_database (GameLibrary): Represents the game library, loaded from a CSV file.
    
    Methods:
        handle_request(request): Determines the type of request and delegates it to the appropriate handler method.
    """
    
    def __init__(self):
        """
        Initializes the Server_Request_Handler with a Credential_Manager, User_Manager, and loads the game
        database from a CSV file. It also adds a default user for demonstration purposes.
        """
        self.credential_manager = Credential_Manager()
        self.user_manager = User_Manager()
        self.game_database = GameLibraryIO.parse_games_from_file("..\\..\\..\\..\\..\\database\\merged_steam_game_database.csv")
        self.credential_manager.add_credential("username", "password")
        self.user_manager.add_user("username", "password")
                
    
    def handle_request(self, request):
        """
        Handles incoming requests by determining the request type and calling the appropriate method
        to process the request.

        Args:
            request (dict): A dictionary containing the request data and type.
        
        Returns:
            dict: A response dictionary containing the results of the request processing or an error message.
        """
        response = {}
        response[constants.KEY_STATUS] = constants.VALUE_FAILURE
        response[constants.KEY_FAILURE_MESSAGE] = "unsupported request type"
        
        request_type = request[constants.KEY_REQUEST_TYPE]
        if request_type == constants.ADD_CREDENTIAL_REQUEST_TYPE:
            response = self._add_new_credential(request)
            
        if request_type == constants.USERNAME_EXIST_REQUEST_TYPE:
            response = self._username_exist(request)
            
        if request_type == constants.GET_SPECIFIED_CREDENTIAL_REQUEST_TYPE:
            response = self._get_specified_credential(request)
            
        if request_type == constants.GET_GAME_LIBRARY_REQUEST_TYPE: 
            response = self._get_game_library(request)
            
        if request_type == constants.GET_ALL_OWNED_GAMES:
            response = self._get_all_owned_games(request)
            
        if request_type == constants.SET_ALL_LIKED_GAMES:
            response = self._set_all_liked_games(request)
            
        if request_type == constants.SET_PREFERRED_GENRES:
            response = self._set_all_perferred_genres(request)
            
        if request_type == constants.GET_ALL_LIKED_GAMES:
            response = self._get_all_liked_games(request)
            
        if request_type == constants.SET_ACTIVE_USER:
            response = self._set_current_active_user(request)
            
        if request_type == constants.GET_ABOUT_ME_DESCRIPTION:
            response = self._get_about_me_description(request)
            
        if request_type == constants.GET_ALL_DISLIKED_GAMES:
            response = self._get_all_disliked_games(request)
            
        if request_type == constants.GET_USER_PROFILE_PICTURE_PATH:
            response = self._get_profile_picture_path(request)
            
        if request_type == constants.GET_FIRST_TIME_LOGIN:
            response = self._get_first_time_login(request)
            
        if request_type == constants.SET_FIRST_TIME_LOGIN:
            response = self._set_first_time_login(request)
            
        if request_type == constants.GET_PREFERRED_GENRES:
            response = self._get_all_perferred_genres(request)
            
        if request_type == constants.SET_ALL_DISLIKED_GAMES:
            response = self._set_all_disliked_games(request)
            
        if request_type == constants.SET_ABOUT_ME_DESCRIPTION:
            response = self._set_about_me_description(request)
            
        if request_type == constants.SET_USER_PROFILE_PICTURE_PATH:
            response = self._set_profile_picture_path(request)
            
        if request_type == constants.SET_ALL_OWNED_GAMES:
            response = self._set_all_owned_games(request)
            
        if request_type == constants.GET_SUGGESTED_GAMES:
            response = self._get_suggested_games(request)
            
        if request_type == constants.SET_SUGGESTED_GAMES:
            response = self._set_suggested_games(request)
            
        return response
    
    def _set_suggested_games(self, request):
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            user.get_suggested_games_game_library().remove_all_games()
            suggested_games = request[constants.KEY_GAMES]
            for game in suggested_games:
                game = self.game_database.find_game_by_id(game["gameID"])
                user.get_suggested_games_game_library().add_game(game)
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    def _get_suggested_games(self, request):
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            suggested_games = [self._game_to_dict(game) for game in user.get_suggested_games_game_library().get_games()]
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_GAMES] = suggested_games
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    def _set_profile_picture_path(self, request):
        """
        Sets the profile picture path for a specified user.
        
        Args:
            request (dict): The request containing the username and the new profile picture path.
        
        Returns:
            dict: The response indicating success or failure.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            description = request[constants.KEY_PATH]
            user.set_user_profile_picture_path(description)
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
            
    def _set_about_me_description(self, request):
        """
        Updates the 'About Me' description for a specified user.
        
        Args:
            request (dict): The request containing the username and the new 'About Me' description.
        
        Returns:
            dict: The response indicating success or failure.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            description = request[constants.KEY_DESCRIPTION]
            user.set_about_me_description(description)
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    
    def _set_all_disliked_games(self, request):
        """
        Sets the disliked games for a specified user, replacing any previously set dislikes.
        
        Args:
            request (dict): The request containing the username and the list of disliked games.
        
        Returns:
            dict: The response indicating success or failure.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            user.get_all_disliked_games_game_library().remove_all_games()
            liked_games = request[constants.KEY_GAMES]
            for game in liked_games:
                game = self.game_database.find_game_by_id(game["gameID"])
                user.get_all_disliked_games_game_library().add_game(game)
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
     
    def _set_first_time_login(self, request):
        """
        Updates the first time login status for a specified user.
        
        Args:
            request (dict): The request containing the username and a boolean indicating the new first time login status.
        
        Returns:
            dict: The response indicating success or failure, including the status of the operation.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            first_time_login = request[constants.KEY_FIRST_TIME_LOGIN]
            user = self.user_manager.get_user(username)
            user.set_first_time_login(first_time_login)
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
         
    def _get_first_time_login(self, request):
        """
        Retrieves the first time login status for a specified user.
        
        Args:
            request (dict): The request containing the username.
        
        Returns:
            dict: The response with the first time login status of the user, including success or failure indicators.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_FIRST_TIME_LOGIN] = user.get_first_time_login() 
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
            
    def _get_about_me_description(self, request):
        """
        Retrieves the 'About Me' description for a specified user.
        
        Args:
            request (dict): The request containing the username.
        
        Returns:
            dict: The response with the 'About Me' description of the user, including success or failure indicators.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_DESCRIPTION] = user.get_about_me_description() 
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    def _get_profile_picture_path(self, request):
        """
        Retrieves the profile picture path for a specified user.
        
        Args:
            request (dict): The request containing the username.
        
        Returns:
            dict: The response with the profile picture path of the user, including success or failure indicators.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_PATH] = user.get_user_profile_picture_path() 
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response

            
    def _add_new_credential(self, request):
        """
        Adds a new set of credentials for a user.
        
        Args:
            request (dict): The request containing the username and password to be added.
        
        Returns:
            dict: The response indicating the success or failure of the operation.
        """
        response = {}
        username = request[constants.KEY_USERNAME]
        password = request[constants.KEY_PASSWORD]
        try:
            self.credential_manager.add_credential(username, password)
            self.user_manager.add_user(username, password)
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            return response
        except:
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            return response
        
    def _username_exist(self, request):
        """
        Checks if a username already exists within the system.
        
        Args:
            request (dict): The request containing the username to check.
        
        Returns:
            dict: The response indicating whether the username exists, along with a success or failure status.
        """

        response = {}
        username = request[constants.KEY_USERNAME]
        if self.credential_manager.username_exist(username):
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            return response
        else:
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            return response
        
        
    def _get_specified_credential(self, request):
        """
        Retrieves the credentials for a specified username.
        
        Args:
            request (dict): The request containing the username whose credentials are to be retrieved.
        
        Returns:
            dict: The response with the credentials if the username exists, including success or failure indicators.
        """
        response = {}
        username = request[constants.KEY_USERNAME]
        if self.credential_manager.username_exist(username):
            credential = self.credential_manager.get_credential(username)
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_USERNAME] = f"{credential.get_username()}"
            response[constants.KEY_PASSWORD] = f"{credential.get_password()}"
            return response
        else:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            return response
        
    def _get_game_library(self, request):
        """
        Retrieves the entire game library.
        
        Args:
            request (dict): The request for retrieving the game library.
        
        Returns:
            dict: The response containing a list of all games in the library, including success or failure indicators.
        """
        response = {}
        try:
            games_list = self.game_database.get_games()
            games_data = [self._game_to_dict(game) for game in games_list]
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_GAMES] = games_data
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response

    def _game_to_dict(self, game):
        """
        Converts a Game object into a dictionary suitable for JSON serialization.
        
        This method is designed to transform a Game object into a dictionary format, making it
        easier to serialize into JSON for API responses or data interchange. It extracts relevant
        game attributes and organizes them into a structured dictionary.
        
        Args:
            game (Game): The Game object to be converted into a dictionary.
        
        Returns:
            dict: A dictionary representation of the Game object, including keys for the game's
            name, genres, game ID, developers, release date (year and month), number of positive
            and negative reviews, average playtime, photo link, and description. Each attribute of
            the game is mapped to a corresponding key-value pair in the dictionary.
            
        Example of returned dictionary:
            {
                "name": "Example Game",
                "genres": ["Action", "Adventure"],  
                "gameID": 123456,
                "developers": "Example Developer",
                "releaseDateYear": 2020,
                "releaseDateMonth": 5,
                "numberPositiveReviews": 1000,
                "numberNegativeReviews": 50,
                "averagePlaytime": 20,
                "photoLink": "http://example.com/game_photo.jpg",
                "description": "An example game description."
            }
        """
        return {
            "name": game.name,
            "genres": [str(genre) for genre in game.genres],  
            "gameID": game.game_id,
            "developers": game.developers,
            "releaseDateYear": game.release_date_year,
            "releaseDateMonth": game.release_date_month,
            "numberPositiveReviews": game.number_positive_reviews,
            "numberNegativeReviews": game.number_negative_reviews,
            "averagePlaytime": game.average_playtime,
            "photoLink": game.game_photo_link,
            "description": game.description
        }
        
    def _get_all_owned_games(self, request):
        """
        Retrieves all games owned by a specified user.
        
        Args:
            request (dict): The request containing the username.
        
        Returns:
            dict: The response with a list of all owned games by the user, including success or failure indicators.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            owned_games = [self._game_to_dict(game) for game in user.get_all_owned_games_game_library().get_games()]
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_GAMES] = owned_games
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    
    def _set_all_liked_games(self, request):
        """
        Sets the liked games for a specified user, replacing any previously set likes.
        
        Args:
            request (dict): The request containing the username and the list of liked games.
        
        Returns:
            dict: The response indicating success or failure, including the status of the operation.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            user.get_all_liked_games_game_library().remove_all_games()
            liked_games = request[constants.KEY_GAMES]
            for game in liked_games:
                game = self.game_database.find_game_by_id(game["gameID"])
                user.get_all_liked_games_game_library().add_game(game)
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    def _set_all_owned_games(self, request):
        """
        Sets the owned games for a specified user, replacing any previously owned games.
        
        Args:
            request (dict): The request containing the username and the list of owned games.
        
        Returns:
            dict: The response indicating success or failure, including the status of the operation.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            user.get_all_owned_games_game_library().remove_all_games()
            liked_games = request[constants.KEY_GAMES]
            for game in liked_games:
                game = self.game_database.find_game_by_id(game["gameID"])
                user.get_all_owned_games_game_library().add_game(game)
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    def _set_all_perferred_genres(self, request):
        """
        Sets the preferred genres for a specified user.
        
        Args:
            request (dict): The request containing the username and the list of preferred genres.
        
        Returns:
            dict: The response indicating success or failure, including the status of the operation.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            preferred_genres = request[constants.KEY_GENRES]
            user.set_preferred_genres([])
            for genre in preferred_genres:
                user.get_preferred_genres().append(genre)
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    def _get_all_perferred_genres(self, request):
        """
        Retrieves the preferred genres for a specified user.
        
        Args:
            request (dict): The request containing the username.
        
        Returns:
            dict: The response with a list of the user's preferred genres, including success or failure indicators.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            preferred_genres = user.get_preferred_genres()
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_GENRES] = preferred_genres
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
            
                
    def _set_current_active_user(self, request):    
        """
        Sets the current active user based on the specified username.
        
        Args:
            request (dict): The request containing the username to be set as the active user.
        
        Returns:
            dict: The response indicating success or failure, along with the status of the operation.
        """
        response = {}
        
        username = request[constants.KEY_USERNAME]
        user = self.user_manager.get_user(username)
        Active_User.set_active_user(user)
        
        response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
        response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        
        return response
    
    def _get_all_liked_games(self, request):
        """
        Retrieves all games liked by a specified user.
        
        Args:
            request (dict): The request containing the username.
        
        Returns:
            dict: The response with a list of all liked games by the user, including success or failure indicators.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            liked_games = [self._game_to_dict(game) for game in user.get_all_liked_games_game_library().get_games()]
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_GAMES] = liked_games
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    
    
    def _get_all_disliked_games(self, request):
        """
        Retrieves all games disliked by a specified user.
        
        Args:
            request (dict): The request containing the username.
        
        Returns:
            dict: The response with a list of all disliked games by the user, including success or failure indicators.
        """
        response = {}
        try:
            username = request[constants.KEY_USERNAME]
            user = self.user_manager.get_user(username)
            disliked_games = [self._game_to_dict(game) for game in user.get_all_disliked_games_game_library().get_games()]
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_GAMES] = disliked_games
        except Exception as e:
            response[constants.KEY_STATUS] = constants.VALUE_FAILURE
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            response[constants.KEY_FAILURE_MESSAGE] = str(e)
        return response
    

            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
