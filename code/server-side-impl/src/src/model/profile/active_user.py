'''
Created on Mar 24, 2024

@author: Jeffrey Gaines
'''

class Active_User:
    """
    A class designed to manage the active user session in an application.

    This class implements the Singleton pattern to ensure that there is only one active
    user at any given time across the application. It provides a centralized point of
    access to the active user's information, allowing for easy management of user
    sessions.

    The class uses class-level methods and a class variable to maintain the state of
    the active user, ensuring that the active user is easily accessible and modifiable
    from any part of the application without the need to instantiate the class.

    Attributes:
        active_user: Stores the active user object. Initially set to None.
    """
    
    active_user = None
    
    @staticmethod
    def get_active_user():
        """
        Retrieves the currently active user.

        This method provides a simple way to access the active user's information from
        anywhere within the application, facilitating features like user preferences,
        session management, and access control.

        Returns:
            The active user object if one is set, otherwise None.
        """
        return Active_User.active_user
    
    
    @staticmethod
    def set_active_user(user):
        """
        Sets or updates the active user.

        This method allows the application to change the active user, ensuring that
        the application can adapt to user logins, logouts, and switches. It is a
        critical component of session management within the application.

        Args:
            user: The user object to be set as the active user. This could be an
            instance of a user class containing user-specific information like
            username, session tokens, and roles.

        Returns:
            None
        """
        Active_User.active_user = user