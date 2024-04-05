'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
from src.model.profile.credentials.credential import Credential

class TestCredential(unittest.TestCase):

    def setUp(self):
        self.username = "test_user"
        self.password = "test_pass"
        self.credential = Credential(self.username, self.password)

    def test_init(self):
        self.assertEqual(self.credential.get_username(), self.username)
        self.assertEqual(self.credential.get_password(), self.password)

        with self.assertRaises(Exception) as context:
            Credential(None, self.password)
        self.assertTrue("username is none" in str(context.exception))

        with self.assertRaises(Exception) as context:
            Credential(self.username, None)
        self.assertTrue("password is none" in str(context.exception))

    def test_set_username(self):
        new_username = "new_test_user"
        self.credential.set_username(new_username)
        self.assertEqual(self.credential.get_username(), new_username)

        with self.assertRaises(Exception) as context:
            self.credential.set_username(None)
        self.assertTrue("username is none" in str(context.exception))

    def test_set_password(self):
        new_password = "new_test_pass"
        self.credential.set_password(new_password)
        self.assertEqual(self.credential.get_password(), new_password)

        with self.assertRaises(Exception) as context:
            self.credential.set_password(None)
        self.assertTrue("password is none" in str(context.exception))

if __name__ == '__main__':
    unittest.main()