import json
from enum import Enum, auto

class Genre(Enum):
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