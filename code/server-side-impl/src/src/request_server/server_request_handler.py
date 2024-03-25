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
    
    def __init__(self):
        self.credential_manager = Credential_Manager()
        self.user_manager = User_Manager()
        self.game_database = GameLibraryIO.parse_games_from_file("..\\..\\..\\..\\..\\database\\testData.csv")
        self.credential_manager.add_credential("username", "password")
                
    
    def handle_request(self, request):
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
        if request_type == constants.SET_ACTIVE_USER:
            response == self._set_current_active_user(request)
        
        return response
            
            
            
    def _add_new_credential(self, request):
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
        response = {}
        username = request[constants.KEY_USERNAME]
        user = self.user_manager.get_user(username)
        liked_games = request[constants.KEY_GAMES]
        
    def _set_current_active_user(self, request):
        response = {}
        
        username = request[constants.KEY_USERNAME]
        user = self.user_manager.get_user(username)
        Active_User.set_active_user(user)
        
        response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
        response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
        
        return response
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
