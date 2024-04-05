'''
Created on Mar 2, 2024

@author: Thomas
'''
import json
from enum import Enum, auto

class Genre(Enum):
    """
    An enumeration representing various genres of video games and related content.

    Each genre is represented as a unique enumeration value. This includes a wide range
    of genres from action and adventure to more specific categories like VR support and
    educational content. The enumeration values are named using uppercase characters 
    and underscores for spaces, ensuring consistency and ease of use in code.

    The `__str__` method is overridden to provide a more human-readable string representation 
    of each genre, converting underscore-separated names into space-separated titles with 
    each word capitalized.
    """
    ACCOUNTING = "ACCOUNTING"
    VIDEO_PRODUCTION = "VIDEO_PRODUCTION"
    RACING = "RACING"
    ONLINE_MULTI_PLAYER = "ONLINE_MULTI_PLAYER"
    LINUX = "LINUX"
    CASUAL = "CASUAL"
    EDUCATION = "EDUCATION"
    STEAM_ACHIEVEMENTS = "STEAM_ACHIEVEMENTS"
    FULL_CONTROLLER_SUPPORT = "FULL_CONTROLLER_SUPPORT"
    SHARED_SPLIT_SCREEN = "SHARED_SPLIT_SCREEN"
    NUDITY = "NUDITY"
    INCLUDES_LEVEL_EDITOR = "INCLUDES_LEVEL_EDITOR"
    SOFTWARE_TRAINING = "SOFTWARE_TRAINING"
    ONLINE_CO_OP = "ONLINE_CO_OP"
    VALVE_ANTI_CHEAT_ENABLED = "VALVE_ANTI_CHEAT_ENABLED"
    WEB_PUBLISHING = "WEB_PUBLISHING"
    GORE = "GORE"
    FREE_TO_PLAY = "FREE_TO_PLAY"
    UTILITIES = "UTILITIES"
    VR_SUPPORT = "VR_SUPPORT"
    GAME_DEVELOPMENT = "GAME_DEVELOPMENT"
    MASSIVELY_MULTIPLAYER = "MASSIVELY_MULTIPLAYER"
    AUDIO_PRODUCTION = "AUDIO_PRODUCTION"
    MULTI_PLAYER = "MULTI_PLAYER"
    DESIGN_ILLUSTRATION = "DESIGN_ILLUSTRATION"
    LOCAL_CO_OP = "LOCAL_CO_OP"
    STEAMVR_COLLECTIBLES = "STEAMVR_COLLECTIBLES"
    INDIE = "INDIE"
    SEXUAL_CONTENT = "SEXUAL_CONTENT"
    SINGLE_PLAYER = "SINGLE_PLAYER"
    EARLY_ACCESS = "EARLY_ACCESS"
    PARTIAL_CONTROLLER_SUPPORT = "PARTIAL_CONTROLLER_SUPPORT"
    MAC = "MAC"
    PHOTO_EDITING = "PHOTO_EDITING"
    STEAM_TURN_NOTIFICATIONS = "STEAM_TURN_NOTIFICATIONS"
    STATS = "STATS"
    STRATEGY = "STRATEGY"
    RPG = "RPG"
    VIOLENT = "VIOLENT"
    COMMENTARY_AVAILABLE = "COMMENTARY_AVAILABLE"
    IN_APP_PURCHASES = "IN_APP_PURCHASES"
    SIMULATION = "SIMULATION"
    ACTION = "ACTION"
    STEAM_LEADERBOARDS = "STEAM_LEADERBOARDS"
    WINDOWS = "WINDOWS"
    ANIMATION_MODELING = "ANIMATION_MODELING"
    HUNTERS = "HUNTERS"
    STEAM_WORKSHOP = "STEAM_WORKSHOP"
    STEAM_TRADING_CARDS = "STEAM_TRADING_CARDS"
    STEAM_CLOUD = "STEAM_CLOUD"
    SPORTS = "SPORTS"
    MMO = "MMO"
    DOCUMENTARY = "DOCUMENTARY"
    TUTORIAL = "TUTORIAL"
    CROSS_PLATFORM_MULTIPLAYER = "CROSS_PLATFORM_MULTIPLAYER"
    LOCAL_MULTI_PLAYER = "LOCAL_MULTI_PLAYER"
    ADVENTURE = "ADVENTURE"
    CAPTIONS_AVAILABLE = "CAPTIONS_AVAILABLE"
    INCLUDES_SOURCE_SDK = "INCLUDES_SOURCE_SDK"
    CO_OP = "CO_OP"
    MISSING_GENRE = "MISSING_GENRE"

    def __str__(self):
        """
        Returns a human-readable string for the genre, converting underscores to spaces
        and capitalizing each word.

        Returns:
            str: The human-readable string representation of the genre.
        """
        return self.name.replace("_", " ").title()

class Game:
    """
    A class to represent a video game with various attributes.
    
    Attributes:
        name (str): The name of the video game.
        genres (list[Genre]): A list of genres associated with the video game, using the Genre Enum.
        game_id (int): A unique identifier for the video game.
        developers (str): The name(s) of the developer(s) of the video game.
        release_date_year (int): The release year of the video game.
        release_date_month (int): The release month of the video game.
        number_positive_reviews (int): The number of positive reviews the video game has received.
        number_negative_reviews (int): The number of negative reviews the video game has received.
        average_playtime (int): The average playtime of the video game in hours.
        game_photo_link (str): A URL link to a photo of the video game.
        description (str): A description of the video game.
        
    The class encapsulates details about a video game, including its name, genre, developers, 
    release date, review scores, average playtime, photo, and a brief description. 
    This representation can be used in various applications, including game libraries 
    or recommendation systems.
    
    The genres attribute utilizes an Enum to ensure consistency and validity of the 
    genre values associated with each game.
    """
    def __init__(self, name, genres, game_id, developers, release_date_year, release_date_month,
                 number_positive_reviews, number_negative_reviews, average_playtime,
                 game_photo_link, description):
        """
        Initializes a new instance of the Game class.
        
        See class documentation for details on the parameters.
        """
        self.name = name
        self.genres = genres
        self.game_id = game_id
        self.developers = developers
        self.release_date_year = release_date_year
        self.release_date_month = release_date_month
        self.number_positive_reviews = number_positive_reviews
        self.number_negative_reviews = number_negative_reviews
        self.average_playtime = average_playtime
        self.game_photo_link = game_photo_link
        self.description = description