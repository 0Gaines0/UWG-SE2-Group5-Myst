import json
from enum import Enum, auto

class Genre(Enum):
    ACCOUNTING = auto()
    VIDEO_PRODUCTION = auto()
    RACING = auto()
    ONLINE_MULTI_PLAYER = auto()
    LINUX = auto()
    CASUAL = auto()
    EDUCATION = auto()
    STEAM_ACHIEVEMENTS = auto()
    FULL_CONTROLLER_SUPPORT = auto()
    SHARED_SPLIT_SCREEN = auto()
    NUDITY = auto()
    INCLUDES_LEVEL_EDITOR = auto()
    SOFTWARE_TRAINING = auto()
    ONLINE_CO_OP = auto()
    VALVE_ANTI_CHEAT_ENABLED = auto()
    WEB_PUBLISHING = auto()
    GORE = auto()
    FREE_TO_PLAY = auto()
    UTILITIES = auto()
    VR_SUPPORT = auto()
    GAME_DEVELOPMENT = auto()
    MASSIVELY_MULTIPLAYER = auto()
    AUDIO_PRODUCTION = auto()
    MULTI_PLAYER = auto()
    DESIGN_ILLUSTRATION = auto()
    LOCAL_CO_OP = auto()
    STEAMVR_COLLECTIBLES = auto()
    INDIE = auto()
    SEXUAL_CONTENT = auto()
    SINGLE_PLAYER = auto()
    EARLY_ACCESS = auto()
    PARTIAL_CONTROLLER_SUPPORT = auto()
    MAC = auto()
    PHOTO_EDITING = auto()
    STEAM_TURN_NOTIFICATIONS = auto()
    STATS = auto()
    STRATEGY = auto()
    RPG = auto()
    VIOLENT = auto()
    COMMENTARY_AVAILABLE = auto()
    IN_APP_PURCHASES = auto()
    SIMULATION = auto()
    ACTION = auto()
    STEAM_LEADERBOARDS = auto()
    WINDOWS = auto()
    ANIMATION_MODELING = auto()
    HUNTERS = auto()
    STEAM_WORKSHOP = auto()
    STEAM_TRADING_CARDS = auto()
    STEAM_CLOUD = auto()
    SPORTS = auto()
    MMO = auto()
    DOCUMENTARY = auto()
    TUTORIAL = auto()
    CROSS_PLATFORM_MULTIPLAYER = auto()
    LOCAL_MULTI_PLAYER = auto()
    ADVENTURE = auto()
    CAPTIONS_AVAILABLE = auto()
    INCLUDES_SOURCE_SDK = auto()
    CO_OP = auto()
    MISSING_GENRE = auto()

    def __str__(self):
        return self.name.replace("_", " ").title()

class Game:
    def __init__(self, name, genres, game_id, developers, release_date_year, release_date_month,
                 number_positive_reviews, number_negative_reviews, average_playtime,
                 game_photo_link, description):
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