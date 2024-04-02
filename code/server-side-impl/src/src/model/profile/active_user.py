'''
Created on Mar 24, 2024

@author: Jeffrey Gaines
'''

class Active_User:
    
    active_user = None
    
    @staticmethod
    def get_active_user():
        return Active_User.active_user
    
    
    @staticmethod
    def set_active_user(user):
        Active_User.active_user = user