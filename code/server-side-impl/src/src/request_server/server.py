'''
Created on Mar 9, 2024

@author: Jeffrey
'''

import zmq
from src.request_server import constants
from src.request_server.server_request_handler import Server_Request_Handler

def log(message):
    print("Server::{0}".format(message))

def main(protocol, ipAddress, port):
    request_handler = Server_Request_Handler()
    
    
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("{0}://{1}:{2}".format(protocol, ipAddress, port))

    log("Server Online:")
    while True:
        log("waiting for request...")
        json_message = socket.recv_json();
        request = json_message
        log("Received request: {0}".format(request))
        if(request["request_type"] == "exit"):
            log("Server Offline...")
            break
        else:
            response = request_handler.handle_request(request)
            log("Sending response: {0}".format(response))
            socket.send_json(response)

if (__name__ == "__main__"):
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
        
