'''
Created on Mar 2, 2024

@author: Thomas
'''
class GameLibrary:
    """
    A class to represent a library of video games.
    
    This class provides methods to add, remove, and search for games within the library,
    allowing for the management of a collection of `Game` objects.
    
    Attributes:
        games (list[Game]): A list of games in the library.
    """
    def __init__(self):
        """
        Initializes a new instance of the GameLibrary class, initially empty.
        """
        self.games = []

    def add_game(self, game):
        """
        Adds a new game to the library.
        
        Args:
            game (Game): The game to add to the library.
            
        Raises:
            ValueError: If the game is None or if a game with the same ID already exists in the library.
        """
        if game is None:
            raise ValueError("Game must not be null")
        if any(existing_game.game_id == game.game_id for existing_game in self.games):
            raise ValueError(f"Duplicate game ID: {game.game_id}")
        self.games.append(game)
        
    def get_games(self):
        """
        Returns a list of all games in the library.
        
        Returns:
            list[Game]: The list of games in the library.
        """
        return self.games

    def remove_game(self, game_id):
        """
        Removes a game from the library by its unique game ID.
        
        Args:
            game_id (int): The ID of the game to remove.
        """
        self.games = [game for game in self.games if game.game_id != game_id]
    
    def remove_all_games(self):
        """
        Clears the library of all games.
        """
        self.games = []

    def find_game_by_id(self, game_id):
        """
        Searches for a game by its ID and returns it if found.
        
        Args:
            game_id (int): The ID of the game to search for.
            
        Returns:
            Game: The game with the matching ID, or None if no match is found.
        """
        for game in self.games:
            if game.game_id == game_id:
                return game
        return None