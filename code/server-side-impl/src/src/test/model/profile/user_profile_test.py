'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from src.model.profile.user_profile import User_Profile
from src.model.games.gamelibrary import GameLibrary
from src.model.games.game import Game, Genre

class TestUserProfile(unittest.TestCase):

    def setUp(self):
        self.username = "test_user"
        self.password = "test_password"
        self.user_profile = User_Profile(self.username, self.password)

    def test_initialization(self):
        self.assertEqual(self.user_profile.get_username(), self.username)
        self.assertEqual(self.user_profile.get_password(), self.password)
        self.assertIsInstance(self.user_profile.get_all_owned_games_game_library(), GameLibrary)
        self.assertIsInstance(self.user_profile.get_all_liked_games_game_library(), GameLibrary)
        self.assertIsInstance(self.user_profile.get_all_disliked_games_game_library(), GameLibrary)
        self.assertEqual(self.user_profile.get_preferred_genres(), [])
        self.assertTrue(self.user_profile.get_first_time_login())
        self.assertEqual(self.user_profile.get_about_me_description(), "")
        self.assertEqual(self.user_profile.get_user_profile_picture_path(), "")

    def test_set_username(self):
        new_username = "new_test_user"
        self.user_profile.set_username(new_username)
        self.assertEqual(self.user_profile.get_username(), new_username)

    def test_set_password(self):
        new_password = "new_test_password"
        self.user_profile.set_password(new_password)
        self.assertEqual(self.user_profile.get_password(), new_password)

    def test_first_time_login(self):
        self.user_profile.set_first_time_login(False)
        self.assertFalse(self.user_profile.get_first_time_login())

    def test_about_me_description(self):
        new_description = "New about me"
        self.user_profile.set_about_me_description(new_description)
        self.assertEqual(self.user_profile.get_about_me_description(), new_description)

    def test_user_profile_picture_path(self):
        new_path = "/path/to/new/picture.jpg"
        self.user_profile.set_user_profile_picture_path(new_path)
        self.assertEqual(self.user_profile.get_user_profile_picture_path(), new_path)

    def test_owned_games_library(self):
        game = Game("Test Game", [Genre.ACTION], 1, "Test Developer", 2024, 4, 100, 50, 10, "http://example.com/testgame.jpg", "A test game")
        self.user_profile.get_all_owned_games_game_library().add_game(game)
        self.assertIn(game, self.user_profile.get_all_owned_games_game_library().get_games())
    
    def test_liked_games_library(self):
        game = Game("Liked Game", [Genre.ADVENTURE], 2, "Test Developer", 2024, 4, 200, 100, 20, "http://example.com/likedgame.jpg", "A liked game")
        self.user_profile.get_all_liked_games_game_library().add_game(game)
        self.assertIn(game, self.user_profile.get_all_liked_games_game_library().get_games())
    
    def test_disliked_games_library(self):
        game = Game("Disliked Game", [Genre.STRATEGY], 3, "Test Developer", 2024, 4, 50, 25, 5, "http://example.com/dislikedgame.jpg", "A disliked game")
        self.user_profile.get_all_disliked_games_game_library().add_game(game)
        self.assertIn(game, self.user_profile.get_all_disliked_games_game_library().get_games())

    def test_preferred_genres(self):
        new_genres = [Genre.ACTION, Genre.ADVENTURE]
        self.user_profile.set_preferred_genres(new_genres)
        self.assertEqual(self.user_profile.get_preferred_genres(), new_genres)

if __name__ == '__main__':
    unittest.main()