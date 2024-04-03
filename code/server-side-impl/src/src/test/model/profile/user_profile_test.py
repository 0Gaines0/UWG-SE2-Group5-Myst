'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from src.model.profile.user_profile import User_Profile

class TestUserProfile(unittest.TestCase):

    def setUp(self):
        self.username = "test_user"
        self.password = "test_password"
        self.user_profile = User_Profile(self.username, self.password)

    def test_initialization(self):
        self.assertEqual(self.user_profile.get_username(), self.username)
        self.assertEqual(self.user_profile.get_password(), self.password)

    def test_set_username(self):
        new_username = "new_test_user"
        self.user_profile.set_username(new_username)
        self.assertEqual(self.user_profile.get_username(), new_username)

    def test_set_password(self):
        new_password = "new_test_password"
        self.user_profile.set_password(new_password)
        self.assertEqual(self.user_profile.get_password(), new_password)

    def test_manage_liked_games(self):
        game_id = 123
        self.user_profile.add_liked_game(game_id)
        self.assertIn(game_id, self.user_profile.get_liked_games())

if __name__ == '__main__':
    unittest.main()