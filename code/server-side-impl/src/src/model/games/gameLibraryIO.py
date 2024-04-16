'''
Created on Mar 2, 2024

@author: Thomas
'''
import csv
from src.model.games.game import Game, Genre
from src.model.games.gamelibrary import GameLibrary


class GameLibraryIO:
    """
    A class to handle Input/Output operations for a game library, specifically
    reading game details from a CSV file and parsing them into Game objects.
    
    Methods:
        parse_csv_line(csv_line): Parses a single line of CSV, considering the possibility of fields enclosed in quotes.
        to_genre(genre_str): Converts a genre string to a Genre enum, handling replacements for spaces and hyphens.
        parse_game(fields): Parses an array of fields into a Game object.
        parse_games_from_file(filename): Reads a CSV file and returns a GameLibrary object populated with games.
    """
    @staticmethod
    def parse_csv_line(csv_line):
        """
        Parses a single line from a CSV file, handling fields that may be enclosed in quotes and contain commas.
        
        Args:
            csv_line (str): A line from a CSV file.
            
        Returns:
            list[str]: A list of fields extracted from the csv_line.
        """
        fields = []
        in_quotes = False
        buffer = ''
        for curr_char in csv_line:
            if curr_char == '"':
                in_quotes = not in_quotes
            elif curr_char == ',' and not in_quotes:
                fields.append(buffer)
                buffer = ''
            else:
                buffer += curr_char
        fields.append(buffer) 
        return fields

    @staticmethod
    def to_genre(genre_str):
        """
        Converts a genre string from the CSV file to a Genre enum value.
        
        Args:
            genre_str (str): The genre string from the CSV file.
            
        Returns:
            Genre: The corresponding Genre enum value, or MISSING_GENRE if the string does not match any known Genre.
        """
        try:
            return Genre[genre_str.upper().replace(" ", "_").replace("-", "_")]
        except KeyError:
            return Genre.MISSING_GENRE

    @staticmethod
    def parse_game(fields):
        """
        Parses an array of strings into a Game object. Expects fields to be in a specific order as per the CSV format.
        
        Args:
            fields (list[str]): An array of fields representing game attributes.
            
        Returns:
            Game: A Game object populated with the provided attributes, or None if parsing fails.
            
        Raises:
            Exception: If there is an error in parsing the fields into a Game object.
        """
        try:
            game_id = int(fields[0])
            name = fields[1].strip('"') 
            release_date_parts = fields[2].split("/")
            release_date_month = int(release_date_parts[0])
            release_date_year = int(release_date_parts[2])
            developers = fields[3]
            genres = [GameLibraryIO.to_genre(genre_str) for genre_str in fields[7].split(";")]
            positive_ratings = int(fields[9])
            negative_ratings = int(fields[10])
            average_playtime = int(fields[11])
            game_photo_link = fields[13]
            description = fields[14]
            return Game(name, genres, game_id, developers, release_date_year, release_date_month,
                        positive_ratings, negative_ratings, average_playtime, game_photo_link, description)
        except Exception as e:
            print(f"Error parsing game from CSV fields: {e}")
            return None

    @staticmethod
    def parse_games_from_file(filename):
        """
        Reads games from a specified CSV file and populates a GameLibrary object with Game instances.
        
        Args:
            filename (str): The path to the CSV file containing game details.
            
        Returns:
            GameLibrary: A GameLibrary object populated with games parsed from the CSV file.
        """
        library = GameLibrary()
        with open(filename, mode='r', encoding='utf-8') as csv_file:
            csv_reader = csv.reader(csv_file)
            next(csv_reader) 
            for row in csv_reader:
                game = GameLibraryIO.parse_game(row)
                if game:
                    library.add_game(game)
        return library