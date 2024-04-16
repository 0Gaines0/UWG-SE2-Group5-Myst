'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from src.model.profile.active_user import Active_User

class TestActiveUser(unittest.TestCase):

    def tearDown(self):
        Active_User.set_active_user(None)

    def test_set_and_get_active_user(self):
        Active_User.set_active_user("test_user")
        self.assertEqual(Active_User.get_active_user(), "test_user")

        test_user_dict = {"username": "test_user", "role": "admin"}
        Active_User.set_active_user(test_user_dict)
        self.assertEqual(Active_User.get_active_user(), test_user_dict)

    def test_get_active_user_when_none(self):
        self.assertIsNone(Active_User.get_active_user())

    def test_reset_active_user_to_none(self):
        Active_User.set_active_user("test_user")
        self.assertIsNotNone(Active_User.get_active_user())
        Active_User.set_active_user(None)
        self.assertIsNone(Active_User.get_active_user())

    def test_overwrite_active_user(self):
        Active_User.set_active_user("initial_user")
        Active_User.set_active_user("new_user")
        self.assertEqual(Active_User.get_active_user(), "new_user")

if __name__ == '__main__':
    unittest.main()
