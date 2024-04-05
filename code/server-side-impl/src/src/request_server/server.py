"""
A server script for handling requests via ZeroMQ.

This script sets up a server that listens for incoming requests over a specified protocol, IP address,
and port. It utilizes the ZeroMQ library for messaging between the client and server, processing each
received request using an instance of `Server_Request_Handler`. The server supports various request types,
including user authentication, game library access, and user profile management.

Functions:
    log(message): Logs messages to the console.
    main(protocol, ipAddress, port): The main function that initializes and runs the server.

Upon receiving a request, the server decodes the JSON message, logs it, and checks the request type. If
the request type is "exit", the server shuts down. Otherwise, it delegates the request to the
`Server_Request_Handler` for processing and then sends back the response to the client.

Example usage:
    To start the server, ensure ZeroMQ is installed and run this script with the desired protocol,
    IP address, and port specified in `constants.py`. The server will listen for incoming requests
    and process them as described.

Attributes:
    protocol (str): The communication protocol used by the server (e.g., "tcp").
    ipAddress (str): The IP address the server binds to.
    port (int): The port number the server listens on for incoming connections.

Dependencies:
    - ZeroMQ: For messaging between client and server.
    - json: For parsing and constructing JSON messages.
    - Server_Request_Handler: For handling the business logic of each request.
    - constants: A module containing constant values like protocol, IP address, and port.
    
    @author: Jeffrey
"""

import zmq
from src.request_server import constants
from src.request_server.server_request_handler import Server_Request_Handler

def log(message):
    """
    Logs a message to the server console.

    Args:
        message (str): The message to be logged.

    Output:
        Prints the message to the server console with a "Server::" prefix.
    """
    print("Server::{0}".format(message))

def main(protocol, ipAddress, port):
    """
    The main function to initialize and run the ZeroMQ server.

    Sets up a ZeroMQ REP (reply) socket, binds it to the specified protocol, IP address,
    and port, and enters a loop to listen for and process incoming requests.

    Args:
        protocol (str): The communication protocol used by the server (e.g., "tcp").
        ipAddress (str): The IP address the server binds to.
        port (int): The port number the server listens on.

    Behavior:
        The server listens indefinitely for incoming requests, processing each one and sending
        a response back. If a request to exit is received, the server shuts down gracefully.
    """
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
        
