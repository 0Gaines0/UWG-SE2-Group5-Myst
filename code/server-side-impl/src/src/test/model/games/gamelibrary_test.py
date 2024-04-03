'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from your_module import GameLibrary, Game, Genre

class TestGameLibrary(unittest.TestCase):

    def setUp(self):
        self.library = GameLibrary()
        self.game1 = Game("Game 1", [Genre.ACTION, Genre.ADVENTURE], 1, "Dev 1", 2020, 1, 100, 10, 15, "link1", "Description 1")
        self.game2 = Game("Game 2", [Genre.STRATEGY, Genre.SIMULATION], 2, "Dev 2", 2021, 2, 200, 20, 25, "link2", "Description 2")

    def test_add_game(self):
        self.library.add_game(self.game1)
        self.assertIn(self.game1, self.library.get_games())
        self.assertEqual(len(self.library.get_games()), 1)

        with self.assertRaises(ValueError):
            self.library.add_game(self.game1)

    def test_remove_game(self):
        self.library.add_game(self.game1)
        self.library.add_game(self.game2)
        self.library.remove_game(1)
        self.assertNotIn(self.game1, self.library.get_games())
        self.assertIn(self.game2, self.library.get_games())

    def test_remove_all_games(self):
        self.library.add_game(self.game1)
        self.library.add_game(self.game2)
        self.library.remove_all_games()
        self.assertEqual(len(self.library.get_games()), 0)

    def test_find_game_by_id(self):
        self.library.add_game(self.game1)
        self.library.add_game(self.game2)
        found_game = self.library.find_game_by_id(1)
        self.assertEqual(found_game, self.game1)
        self.assertIsNone(self.library.find_game_by_id(3))

    def test_add_game_null_check(self):
        with self.assertRaises(ValueError):
            self.library.add_game(None)

if __name__ == '__main__':
    unittest.main()
