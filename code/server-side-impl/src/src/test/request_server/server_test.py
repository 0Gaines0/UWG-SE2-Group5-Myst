'''
Created on April 3, 2024

@author: Thomas
'''
import unittest
import zmq
import json
import time
from threading import Thread
from src.request_server import constants
from src.request_server.server import main as server_main

class TestServer(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.server_thread = Thread(target=server_main, args=(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT,))
        cls.server_thread.daemon = True
        cls.server_thread.start()
        time.sleep(1)  

    @classmethod
    def tearDownClass(cls):
        context = zmq.Context()
        socket = context.socket(zmq.REQ)
        socket.connect(f"{constants.PROTOCOL}://{constants.IP_ADDRESS}:{constants.PORT}")
        socket.send_json({"request_type": "exit"})
        socket.recv_json()
        cls.server_thread.join()

    def setUp(self):
        self.context = zmq.Context()
        self.socket = self.context.socket(zmq.REQ)
        self.socket.connect(f"{constants.PROTOCOL}://{constants.IP_ADDRESS}:{constants.PORT}")

    def tearDown(self):
        self.socket.close()
        self.context.term()

    def send_request(self, request_type, data=None):
        request = {"request_type": request_type}
        if data is not None:
            request.update(data)
        self.socket.send_json(request)
        return self.socket.recv_json()

    def test_handle_request_valid(self):
        response = self.send_request("valid_request_type")
        self.assertEqual(response.get("status"), "success")

    def test_handle_request_invalid(self):
        response = self.send_request("invalid_request_type")
        self.assertEqual(response.get("status"), "error")

if __name__ == '__main__':
    unittest.main()