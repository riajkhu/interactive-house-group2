import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectHandler 
{
	public void handleConnection()
	{
		ServerSocket handler = null;
		Socket clientSocket = null;
		SharedObject sharedObject = new SharedObject();
		int clientID = 0;
		
		BufferedReader br;
		PrintWriter pw;
		
		try 
		{
			//socket that the server listens to
			handler = new ServerSocket(8000);
			System.out.println("Server starts");
			
			while(true)
			{
				
				//accept a new client, creates a server thread that will talk to it
				clientSocket = handler.accept();
				
				System.out.println("Client accepted, now start");
				
				br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				pw = new PrintWriter(clientSocket.getOutputStream(), true);
				
				
				pw.println("ok");
				//comment if not needed
				String type= br.readLine();
				System.out.println(type);
				
				System.out.println("OK");
				
				//this code is used to check which client connects, uncomment if the client send any of that information
				
				if(type.equals("client"))
				{
					ServerSqlConnector server = new ServerSqlConnector(clientSocket, sharedObject, clientID);
					server.start();
				}
				else if(type.equals("arduno"))
				{
					ArdHandler ardClient = new ArdHandler(clientSocket, sharedObject, clientID);
					ardClient.start();
				}
				else if(type.equals("software"))
				{
					SoftwareHandler sofClient = new SoftwareHandler(clientSocket, sharedObject, clientID);
					sofClient.start();
				}
				//and comment these 2 lines
//				ServerSqlConnector server = new ServerSqlConnector(clientSocket, sharedObject, clientID);
//				server.start();
				
				//clientID
				clientID++;

			}
			
		} 
		catch (IOException e)
		{
			System.out.println("Error in handler");
			e.printStackTrace();
		}		
	}
}
