import csv
from src.model.games.game import Game, Genre
from src.model.games.gamelibrary import GameLibrary


class GameLibraryIO:
    @staticmethod
    def parse_csv_line(csv_line):
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
        fields.append(buffer)  # Add last field
        return fields

    @staticmethod
    def to_genre(genre_str):
        try:
            return Genre[genre_str.upper().replace(" ", "_").replace("-", "_")]
        except KeyError:
            return Genre.MISSING_GENRE

    @staticmethod
    def parse_game(fields):
        try:
            game_id = int(fields[0])
            name = fields[1].strip('"')  # Assuming the name field might be enclosed in quotes
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
        library = GameLibrary()
        with open(filename, mode='r', encoding='utf-8') as csv_file:
            csv_reader = csv.reader(csv_file)
            next(csv_reader)  # Skip header row
            for row in csv_reader:
                game = GameLibraryIO.parse_game(row)
                if game:
                    library.add_game(game)
        return library