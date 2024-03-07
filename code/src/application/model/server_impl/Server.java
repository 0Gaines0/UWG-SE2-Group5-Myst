package application.model.server_impl;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Server {

	private static final String CONNECTION_PROTCOL_IP_PORT = "tcp://127.0.0.1:5555";
	
	/**
	 * Send request.
	 *
	 * @param request the request
	 * @return the string
	 */
	public static String sendRequest(String request) {
		if (request == null) {
			throw new IllegalArgumentException("request must not be null");
		}
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REQ);
		socket.connect(Server.CONNECTION_PROTCOL_IP_PORT);
		socket.send(request.getBytes(ZMQ.CHARSET), 0);
		byte[] reply = socket.recv(0);
		String response = new String(reply, ZMQ.CHARSET);
		if (response.equals("Invalid Request Format")) {
			throw new IllegalArgumentException("Must provide a valid request type (see README)");
		}
		return response;
	}
}
