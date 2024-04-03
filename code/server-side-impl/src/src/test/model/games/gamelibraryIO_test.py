'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from unittest.mock import patch
from io import StringIO
from src.model.games.game import Game, Genre
from src.model.games.gamelibrary import GameLibrary
from src.model.games.gamelibraryio import GameLibraryIO

class TestGameLibraryIO(unittest.TestCase):

    def test_parse_csv_line(self):
        line = '1,"Game Name",1/1/2020,"Developer",Genre1;Genre2,10,5,15,"http://example.com","Description"'
        expected = ['1', 'Game Name', '1/1/2020', 'Developer', 'Genre1;Genre2', '10', '5', '15', 'http://example.com', 'Description']
        self.assertEqual(GameLibraryIO.parse_csv_line(line), expected)

        line_with_quotes = '1,"Game, Name",1/1/2020,"Developer",Genre1;Genre2,10,5,15,"http://example.com","Description, with comma"'
        expected_with_quotes = ['1', 'Game, Name', '1/1/2020', 'Developer', 'Genre1;Genre2', '10', '5', '15', 'http://example.com', 'Description, with comma']
        self.assertEqual(GameLibraryIO.parse_csv_line(line_with_quotes), expected_with_quotes)

    def test_to_genre(self):
        self.assertEqual(GameLibraryIO.to_genre("ACTION"), Genre.ACTION)
        self.assertEqual(GameLibraryIO.to_genre("missing_genre"), Genre.MISSING_GENRE)

    @patch('src.model.games.gamelibraryio.GameLibraryIO.parse_csv_line')
    def test_parse_game(self, mock_parse_csv_line):
        mock_parse_csv_line.return_value = ['1', 'Game Name', '1/1/2020', 'Developer', 'ACTION;ADVENTURE', '100', '50', '10', 'http://example.com/game_photo.jpg', 'An example game.']
        fields = mock_parse_csv_line.return_value
        game = GameLibraryIO.parse_game(fields)
        self.assertIsInstance(game, Game)
        self.assertEqual(game.name, 'Game Name')
        self.assertIn(Genre.ACTION, game.genres)
        self.assertIn(Genre.ADVENTURE, game.genres)

    @patch('builtins.open', new_callable=patch.mock_open, read_data='id,name,date,developer,genres,pos_reviews,neg_reviews,playtime,photo,description\n1,"Game Name",1/1/2020,"Developer","ACTION;ADVENTURE",100,50,10,"http://example.com","An example game."')
    def test_parse_games_from_file(self, mock_file):
        library = GameLibraryIO.parse_games_from_file("dummy_path.csv")
        self.assertIsInstance(library, GameLibrary)
        self.assertEqual(len(library.get_games()), 1)
        game = library.get_games()[0]
        self.assertEqual(game.name, 'Game Name')
        self.assertEqual(game.game_photo_link, 'http://example.com')

if __name__ == '__main__':
    unittest.main()