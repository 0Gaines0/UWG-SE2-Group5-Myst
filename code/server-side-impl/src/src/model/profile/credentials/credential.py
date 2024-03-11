'''
Created on Mar 10, 2024

@author: Jeffrey
'''
    
class Credential:
    """
    A class to represent user credentials.

    Attributes:
        username (str): The username of the user.
        password (str): The password of the user.
    """

    def __init__(self, username, password):
        """
        Initializes a Credential object with the given username and password.

        Args:
            username (str): The username for the credential.
            password (str): The password for the credential.

        Raises:
            Exception: If username or password is None.
        """
        if username is None:
            raise Exception("username is none")
        if password is None:
            raise Exception("password is none")
        self.username = username
        self.password = password

    def get_username(self):
        """
        Returns the username of the credential.

        Returns:
            str: The username of the credential.
        """
        return self.username
    
    def set_username(self, new_username):
        """
        Sets a new username for the credential.

        Args:
            new_username (str): The new username to be set.

        Raises:
            Exception: If new_username is None.
        """
        if new_username is None:
            raise Exception("username is none")
        self.username = new_username

    def get_password(self):
        """
        Returns the password of the credential.

        Returns:
            str: The password of the credential.
        """
        return self.password
    
    def set_password(self, new_password):
        """
        Sets a new password for the credential.

        Args:
            new_password (str): The new password to be set.

        Raises:
            Exception: If new_password is None.
        """
        if new_password is None:
            raise Exception("password is none")
        self.password = new_password
