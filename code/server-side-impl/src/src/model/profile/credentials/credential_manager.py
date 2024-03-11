'''
Created on Mar 10, 2024
@author: Jeffrey
'''

from src.model.profile.credentials.credential import Credential

class Credential_Manager:
    """
    A class to manage user credentials.

    Attributes:
        user_credentials (list): A list to store user Credential objects.
    """

    def __init__(self):
        """
        Initializes a Credential_Manager object with an empty list of user credentials.
        """
        self.user_credentials = []

    def get_user_credentials(self):
        """
        Returns the list of user credentials.

        Returns:
            list: A list of Credential objects representing user credentials.
        """
        return self.user_credentials
    
    def add_credential(self, username, password):
        """
        Adds a new credential to the list of user credentials.

        Args:
            username (str): The username for the new credential.
            password (str): The password for the new credential.

        Returns:
            bool: True if the credential was added successfully, False otherwise.

        Raises:
            Exception: If username or password is None.
        """
        if username is None:
            raise Exception("username is none")
        if password is None:
            raise Exception("password is none")
        if self.username_exist(username):
            raise Exception("Username '{}' already exist".format(username))
        else:
            credential = Credential(username, password)
            self.user_credentials.append(credential)
            return True
    
    def username_exist(self, username):
        """
        Checks if a username already exists in the list of user credentials.

        Args:
            username (str): The username to check.

        Returns:
            bool: True if the username exists, False otherwise.

        Raises:
            Exception: If username is None.
        """
        if username is None:
            raise Exception("username is none")
        credential = self.get_credential(username)
        if credential is not None:
            return True
        return False

        
    def get_credential(self, username):
        """
        Retrieves a credential from the list of user credentials based on the username.

        Args:
            username (str): The username of the credential to retrieve.

        Returns:
            Credential: The Credential object if found, None otherwise.

        Raises:
            Exception: If username is None.
        """
        if username is None:
            raise Exception("username is none")
        
        for credential in self.user_credentials:
            curr_username = credential.get_username().lower()
            if username.lower() == curr_username:
                return credential
        return None
    
    def change_credential_username(self, username, new_username):
        """
        Changes the username of a credential.

        Args:
            username (str): The current username of the credential.
            new_username (str): The new username to set for the credential.

        Returns:
            bool: True if the username was changed successfully, False otherwise.

        Raises:
            Exception: If username or new_username is None.
        """
        if username is None:
            raise Exception("username is none")
        if new_username is None:
            raise Exception("new_username is none")
        credential = self.get_credential(username)
        potential_credential = self.get_credential(new_username)
        if credential is not None and potential_credential is None:
            credential.set_username(new_username)
            return True
        return False