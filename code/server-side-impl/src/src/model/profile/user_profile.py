'''
Created on Mar 24, 2024

@author: Jeffrey
'''

from src.model.games.gamelibrary import GameLibrary


class User_Profile:
    """
    A class to represent a user's profile within an application.

    This class encapsulates user information including credentials (username and password),
    owned, liked, and disliked games collections, preferred genres, and additional personal
    details such as an 'about me' description and a profile picture path. It also tracks
    whether the user is logging in for the first time.

    Attributes:
        username (str): The username of the user.
        password (str): The user's password.
        allOwnedGames (GameLibrary): A GameLibrary object holding all games owned by the user.
        allLikedGames (GameLibrary): A GameLibrary object holding all games liked by the user.
        allDislikedGames (GameLibrary): A GameLibrary object holding all games disliked by the user.
        preferredGenres (list): A list of preferred genres by the user.
        first_time_login (bool): A flag indicating if the user is logging in for the first time.
        about_me_description (str): A short description about the user.
        user_profile_picture_path (str): The file path to the user's profile picture.
    """
    
    def __init__(self, username, password):
        """
        Initializes a new instance of the User_Profile class with the provided username and password,
        along with initializing empty game libraries for owned, liked, and disliked games, an empty list
        for preferred genres, and default values for first time login, about me description, and profile
        picture path.
        
        Args:
            username (str): The username for the user's profile.
            password (str): The password for the user's profile.
        
        Raises:
            Exception: If either the username or password is None.
        """
        if username is None:
            raise Exception("username is none")
        if password is None:
            raise Exception("password is none")
        self.username = username
        self.password = password
        self.allOwnedGames = GameLibrary()
        self.allLikedGames = GameLibrary()
        self.allDislikedGames = GameLibrary()
        self.suggestedGames = GameLibrary()
        self.preferredGenres = []
        
        self.first_time_login = True
        
        self.about_me_description = ""
        self.user_profile_picture_path = ""

    def get_first_time_login(self):
        """
        Retrieves the first time login status for the user.
        
        This method is useful for determining if additional introductory steps or
        information should be presented to the user upon login.
        
        Returns:
            bool: True if the user is logging in for the first time, False otherwise.
        """
        return self.first_time_login
    
    def set_first_time_login(self, option):
        """
        Sets the first time login status for the user.
        
        This method allows the application to update the user's login status, typically
        after the user has completed an initial setup or introduction.
        
        Args:
            option (bool): The first time login status to be set for the user.
        """
        self.first_time_login = option

    def get_about_me_description(self):
        """
        Retrieves the user's 'About Me' description.
        
        This method provides access to the personal description the user has set in
        their profile, which can be displayed in the user's profile view.
        
        Returns:
            str: The user's 'About Me' description.
        """
        return self.about_me_description
    
    def set_about_me_description(self, description):
        """
        Sets the user's 'About Me' description.
        
        This method allows the user to update their personal description in their
        profile. It can be used in a profile edit feature.
        
        Args:
            description (str): The new 'About Me' description for the user.
        """
        self.about_me_description = description
        
    def get_user_profile_picture_path(self):
        """
        Retrieves the file path to the user's profile picture.
        
        This method is used to access the location of the user's profile picture,
        allowing it to be displayed in the user's profile view or elsewhere in the
        application.
        
        Returns:
            str: The file path to the user's profile picture.
        """
        return self.user_profile_picture_path
    
    def set_user_profile_picture_path(self, path):
        """
        Sets the file path for the user's profile picture.
        
        This method allows the user to update the location of their profile picture,
        typically after uploading a new picture or changing their current one.
        
        Args:
            path (str): The new file path for the user's profile picture.
        """
    
        self.user_profile_picture_path = path
        
    def get_username(self):
        """
        Retrieves the user's username.
        
        This method provides access to the user's username, which can be used for
        identification and authentication purposes within the application.
        
        Returns:
            str: The user's username.
        """
        return self.username
    
    def set_username(self, new_username):
        """
        Sets the user's username.
        
        This method allows the user to change their username. It can be part of a
        profile edit feature within the application. Care should be taken to ensure
        the new username is unique and meets the application's requirements.
        
        Args:
            new_username (str): The new username for the user.
            
        Raises:
            Exception: If the new username is None.
        """
        if new_username is None:
            raise Exception("new_username is none")
        self.username = new_username
    
    def get_password(self):
        """
        Retrieves the user's password.
        
        This method provides access to the user's password. It's important to handle
        password data securely within the application, including using encryption and
        avoiding unnecessary exposure.
        
        Returns:
            str: The user's password.
        """
        return self.password
    
    def set_password(self, new_password):
        """
        Sets the user's password.
        
        This method allows the user to change their password, enhancing security and
        user control over their account. The new password should be securely stored
        and handled within the application.
        
        Args:
            new_password (str): The new password for the user.
            
        Raises:
            Exception: If the new password is None.
        """
        if new_password is None:
            raise Exception("new_password is none")
        self.password = new_password

    def get_all_owned_games_game_library(self):
        """
        Retrieves the GameLibrary containing all games owned by the user.
        
        This method provides access to the collection of games that the user owns,
        allowing for display and interaction within the application.
        
        Returns:
            GameLibrary: The GameLibrary of owned games.
        """
        return self.allOwnedGames
    
    def get_suggested_games_game_library(self):
        return self.suggestedGames
    
    def get_all_liked_games_game_library(self):
        """
        Retrieves the GameLibrary containing all games liked by the user.
        
        This method provides access to the user's liked games, useful for recommendations
        and displaying user preferences.
        
        Returns:
            GameLibrary: The GameLibrary of liked games.
        """
        return self.allLikedGames
    
    def get_all_disliked_games_game_library(self):
        """
        Retrieves the GameLibrary containing all games disliked by the user.
        
        This method provides access to the games the user has disliked, which can be used
        for filtering out recommendations and tailoring the user's experience.
        
        Returns:
            GameLibrary: The GameLibrary of disliked games.
        """
    
        return self.allDislikedGames
    
    def get_preferred_genres(self):
        """
        Retrieves the list of genres preferred by the user.
        
        This method allows the application to access the user's genre preferences,
        which can be used to tailor content, recommendations, and the overall user
        experience.
        
        Returns:
            list: A list of the user's preferred genres.
        """
        return self.preferredGenres
    
    def set_preferred_genres(self, genres):
        """
        Sets the list of genres preferred by the user.
        
        This method allows the user to update their genre preferences, enhancing the
        personalization of content and recommendations within the application.
        
        Args:
            genres (list): The new list of preferred genres for the user.
        """
        self.preferredGenres = genres    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    