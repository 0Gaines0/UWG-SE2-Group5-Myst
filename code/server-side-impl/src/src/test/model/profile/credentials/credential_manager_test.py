'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from src.model.profile.credentials.credential import Credential
from src.model.profile.credentials.credential_manager import Credential_Manager

class TestCredentialManager(unittest.TestCase):

    def setUp(self):
        self.manager = Credential_Manager()

    def test_add_credential(self):
        result = self.manager.add_credential("user1", "password1")
        self.assertTrue(result)
        self.assertEqual(len(self.manager.get_user_credentials()), 1)


        with self.assertRaises(Exception) as context:
            self.manager.add_credential("user1", "password2")
        self.assertIn("Username 'user1' already exist", str(context.exception))

        with self.assertRaises(Exception):
            self.manager.add_credential(None, "password")
        with self.assertRaises(Exception):
            self.manager.add_credential("user2", None)

    def test_username_exist(self):
        self.manager.add_credential("user1", "password1")
        self.assertTrue(self.manager.username_exist("user1"))
        self.assertFalse(self.manager.username_exist("user2"))

        with self.assertRaises(Exception):
            self.manager.username_exist(None)

    def test_get_credential(self):
        self.manager.add_credential("user1", "password1")
        credential = self.manager.get_credential("user1")
        self.assertIsInstance(credential, Credential)
        self.assertEqual(credential.get_username(), "user1")

        self.assertIsNone(self.manager.get_credential("user2"))

        with self.assertRaises(Exception):
            self.manager.get_credential(None)

    def test_change_credential_username(self):
        self.manager.add_credential("user1", "password1")
        result = self.manager.change_credential_username("user1", "new_user1")
        self.assertTrue(result)
        self.assertTrue(self.manager.username_exist("new_user1"))

        self.manager.add_credential("user2", "password2")
        result = self.manager.change_credential_username("new_user1", "user2")
        self.assertFalse(result)

        result = self.manager.change_credential_username("user3", "new_user3")
        self.assertFalse(result)

        with self.assertRaises(Exception):
            self.manager.change_credential_username(None, "new_user")
        with self.assertRaises(Exception):
            self.manager.change_credential_username("user1", None)

if __name__ == '__main__':
    unittest.main()