class GameLibrary:
    def __init__(self):
        self.games = []

    def add_game(self, game):
        if game is None:
            raise ValueError("Game must not be null")
        if any(existing_game.game_id == game.game_id for existing_game in self.games):
            raise ValueError(f"Duplicate game ID: {game.game_id}")
        self.games.append(game)
        
    def get_games(self):
        return self.games

    def remove_game(self, game_id):
        self.games = [game for game in self.games if game.game_id != game_id]
    
    def remove_all_games(self):
        self.games = []

    def find_game_by_id(self, game_id):
        for game in self.games:
            if game.game_id == game_id:
                return game
        return None