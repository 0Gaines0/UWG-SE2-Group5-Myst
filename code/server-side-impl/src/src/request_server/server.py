'''
Created on Mar 9, 2024

@author: Jeffrey
'''

import zmq
import json
from src.request_server import constants

def log(message):
    print("Server::{0}".format(message))

def main(protocol, ipAddress, port):

    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("{0}://{1}:{2}".format(protocol, ipAddress, port))

    log("Server Online...")
    while True:
        log("waiting for request...")
        json_message = socket.recv_string()
        request = json.loads(json_message )
        log("Received request: {0}".format(request))
        if(request == "exit"):
            return
        

if (__name__ == "__main__"):
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
        
