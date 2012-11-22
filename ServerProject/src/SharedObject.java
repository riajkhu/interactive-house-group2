import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class SharedObject {

	ArrayList<ClientsForSharedObject> clients = new ArrayList();

	ArrayList<ClientsForSharedObject> softwareClients = new ArrayList();
	
	ArrayList<ClientsForSharedObject> ardClients = new ArrayList();
	//ArrayList<ClientsForSharedObject>
	
	public SharedObject() 
	{
	}
	
	public void removeClient(int clientID) {
		
		// Remove Client from clients(arraylist)
		
		for (int i = clients.size()-1; i >= 0; i--) {
			
			if (clients.get(i).getID() == clientID) {
				clients.remove(i);
				System.out.println("[SharedObject] : Client Removed.");
				break;
			}
			
		}
		System.out.println("[SharedObject] : No Clients Found.");
	}
	

	public void addClient(Socket clientSocket, int clientID) {
		
		// Add client to SharedClients
		
		clients.add(new ClientsForSharedObject(clientSocket, clientID));
		System.out.println("[SharedObject] : Client Added.");
	}
	
	//added method
	public void addArdClient(Socket clientSocket, int clientID){
		ardClients.add(new ClientsForSharedObject(clientSocket, clientID));
		System.out.println("[SharedObject] : Special client added.");
	}
	
	public void addSoftwareClient(Socket clientSocket, int clientID){
		softwareClients.add(new ClientsForSharedObject(clientSocket, clientID));
		System.out.println("[SharedObject] : Special software client added.");
	}
	
	
	
	public void sendInformation(int clientID, String info) {
		
		// Print to every EXCEPT the client who send the command
		
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getID() != clientID) {
				try {
					Socket socket = clients.get(i).getSocket();
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					
					pw.println(info);
					
				} catch(Exception e) {
					System.out.println("[SharedObject] : Error " + e);
				}
			}
		}
		System.out.println("[SharedObject] : Sent Information To All Clients");
	}
	
	//added method
		public void sendInformationArd(int clientID, String info){
			for (int i = 0; i < ardClients.size(); i++){
				try {
					Socket socket = ardClients.get(i).getSocket();
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					
					pw.println(info);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		//added method
		public void sendInformationSoftware(int clientID, String info){
			for (int i = 0; i < softwareClients.size(); i++){
				try {
					Socket socket = softwareClients.get(i).getSocket();
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					
					pw.println(info);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	
	
}
