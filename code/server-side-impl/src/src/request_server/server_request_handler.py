'''
Created on Mar 10, 2024

@author: Jeffrey
'''

from src.request_server import constants
from src.model.profile.credentials.credential_manager import Credential_Manager

class Server_Request_Handler:
    
    def __init__(self):
        self.credential_manager = Credential_Manager()
        
    
    def handle_request(self, request):
        response = {}
        response[constants.KEY_STATUS] = constants.VALUE_FAILURE
        response[constants.KEY_FAILURE_MESSAGE] = "unsupported request type"
        
        request_type = request[constants.KEY_REQUEST_TYPE]
        if request_type == constants.ADD_CREDENTIAL_REQUEST_TYPE:
            response = self._add_new_credential(request)
        if request_type == constants.USERNAME_EXIST_REQUEST_TYPE:
            response = self._username_exist(request)
        
        return response
            
            
            
    def _add_new_credential(self, request):
        response = {}
        username = request[constants.KEY_USERNAME]
        password = request[constants.KEY_PASSWORD]
        try:
            self.credential_manager.add_credential(username, password)
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            return response
        except:
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            return response
        
    def _username_exist(self, request):
        response = {}
        username = request[constants.KEY_USERNAME]
        if self.credential_manager.username_exist(username):
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_TRUE
            return response
        else:
            response[constants.KEY_STATUS] = constants.VALUE_ACCEPTED
            response[constants.KEY_SUCCESS] = constants.VALUE_FALSE
            return response
            
