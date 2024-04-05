'''
Created on Mar 24, 2024

@author: Jeffrey
'''

from src.model.profile.user_profile import User_Profile


class User_Manager:
    """
    A class to manage user accounts within an application.
    
    This class provides functionalities to add new users to the application and
    retrieve details of existing users by their username. It acts as a central
    repository for user information, ensuring that user data is managed and
    accessed in a consistent and controlled manner.
    
    Attributes:
        users (list[User_Profile]): A list of User_Profile objects representing the users
        of the application.
    """

    def __init__(self):
        """
        Initializes a new instance of the User_Manager class, initially with no users.
        """
        self.users = []

    def get_user(self, username):
        """
        Retrieves a user profile by username.
        
        Searches the list of users for a profile matching the provided username. If a
        match is found, the corresponding User_Profile object is returned. If no match
        is found, None is returned.
        
        Args:
            username (str): The username of the user to retrieve.
        
        Returns:
            User_Profile: The user profile with the matching username, or None if no match is found.
        
        Raises:
            Exception: If the username argument is None, indicating an invalid or missing username.
        """
        if username is None:
            raise Exception("username is none")
        for user in self.users:
            if user.get_username() == username:
                return user
            
        return None
    
    def add_user(self, username, password):
        """
        Adds a new user to the application.
        
        Creates a new User_Profile object with the provided username and password and
        appends it to the list of users. This method allows for the dynamic addition of
        users to the application's user management system.
        
        Args:
            username (str): The username for the new user.
            password (str): The password for the new user.
        
        Raises:
            Exception: If either the username or password arguments are None, indicating missing user information.
        """
        if username is None:
            raise Exception("username is none")
        if password is None:
            raise Exception("password is none")
        user = User_Profile(username, password)
        self.users.append(user)