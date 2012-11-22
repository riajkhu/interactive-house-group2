import java.net.Socket;


public class ClientsForSharedObject {

	private int clientID;
	private Socket socket;
	
	
	public ClientsForSharedObject(Socket socket, int clientID) {
		this.clientID = clientID;
		this.socket = socket;
	}
	
	
	public int getID() {
		return clientID;
	}
	
	public Socket getSocket() {
		return socket;
	}
}
