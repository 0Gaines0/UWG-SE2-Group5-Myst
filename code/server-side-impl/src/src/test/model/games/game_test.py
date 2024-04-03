'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from your_module import Game, Genre

class TestGame(unittest.TestCase):

    def setUp(self):
        self.game_name = "Example Game"
        self.game_genres = [Genre.ACTION, Genre.ADVENTURE]
        self.game_id = 123
        self.developers = "Example Developer"
        self.release_year = 2020
        self.release_month = 5
        self.positive_reviews = 100
        self.negative_reviews = 10
        self.average_playtime = 20
        self.photo_link = "http://example.com/game_photo.jpg"
        self.description = "An example game."

        self.game = Game(self.game_name, self.game_genres, self.game_id, self.developers, 
                         self.release_year, self.release_month, self.positive_reviews, 
                         self.negative_reviews, self.average_playtime, self.photo_link, 
                         self.description)

    def test_init(self):
        self.assertEqual(self.game.name, self.game_name)
        self.assertEqual(self.game.genres, self.game_genres)
        self.assertEqual(self.game.game_id, self.game_id)
        self.assertEqual(self.game.developers, self.developers)
        self.assertEqual(self.game.release_date_year, self.release_year)
        self.assertEqual(self.game.release_date_month, self.release_month)
        self.assertEqual(self.game.number_positive_reviews, self.positive_reviews)
        self.assertEqual(self.game.number_negative_reviews, self.negative_reviews)
        self.assertEqual(self.game.average_playtime, self.average_playtime)
        self.assertEqual(self.game.game_photo_link, self.photo_link)
        self.assertEqual(self.game.description, self.description)


if __name__ == '__main__':
    unittest.main()