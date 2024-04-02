'''
Created on Mar 24, 2024

@author: Jeffrey
'''

from src.model.profile.user_profile import User_Profile


class User_Manager:

    def __init__(self):
        self.users = []

    def get_user(self, username):
        if username is None:
            raise Exception("username is none")
        for user in self.users:
            if user.get_username() == username:
                return user
            
        return None
    
    def add_user(self, username, password):
        if username is None:
            raise Exception("username is none")
        if password is None:
            raise Exception("password is none")
        user = User_Profile(username, password)
        self.users.append(user)