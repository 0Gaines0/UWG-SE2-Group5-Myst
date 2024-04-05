'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from src.model.profile.user_manager import User_Manager
from src.model.profile.user_profile import User_Profile

class TestUserManager(unittest.TestCase):

    def setUp(self):
        self.manager = User_Manager()

    def test_add_user(self):
        self.manager.add_user("user1", "password1")
        self.assertEqual(len(self.manager.users), 1)

        self.manager.add_user("user2", "password2")
        self.assertEqual(len(self.manager.users), 2)

        with self.assertRaises(Exception) as context:
            self.manager.add_user(None, "password3")
        self.assertTrue("username is none" in str(context.exception))

        with self.assertRaises(Exception) as context:
            self.manager.add_user("user4", None)
        self.assertTrue("password is none" in str(context.exception))

    def test_get_user(self):
        self.manager.add_user("user1", "password1")
        user = self.manager.get_user("user1")
        self.assertIsInstance(user, User_Profile)
        self.assertEqual(user.get_username(), "user1")

        self.assertIsNone(self.manager.get_user("nonexistent_user"))

        with self.assertRaises(Exception) as context:
            self.manager.get_user(None)
        self.assertTrue("username is none" in str(context.exception))

if __name__ == '__main__':
    unittest.main()