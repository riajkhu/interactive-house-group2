import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.Socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ServerSqlConnector extends Thread
{
	private Socket clientSocket;
	private Connection connect;
	
	private InputStreamReader isr;
	private OutputStream os;
	
	private boolean successLogin;
	
	
	int clientID;
	SharedObject sharedObject;
	
	public ServerSqlConnector(Socket clientSocket,SharedObject sharedObject,int clientID)
	{
		this.clientSocket = clientSocket;
		successLogin = false;
		this.sharedObject = sharedObject;
		this.clientID = clientID;
		
		
		sharedObject.addClient(clientSocket, clientID);
	}
	
	
	public void run()
	{
		try
		{	
			readDatabase();
//			
			os = clientSocket.getOutputStream();
			isr = new InputStreamReader(clientSocket.getInputStream());
			
			BufferedReader br = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(os);
			
			 //BUG, even if the client have disconnected the server will continue, you will get a nullpointerException since the readline on line 92 will read null  
			while(!clientSocket.isClosed())
			{

				//username = testman   password = 123
				
				//comment if the login part is not needed
				//login part, will restart as long as the user put in the wrong username/password. 
//				while(!successLogin)
//				{
//					System.out.println("Reading username and password");
//					String device = br.readLine();
//					String[] parse = device.split(":");
//				
//					System.out.println("Creating statement FOR THE LOGIN");
//					Statement stmt = connect.createStatement();
//					ResultSet rs = stmt.executeQuery("SELECT userName,password FROM users WHERE userName='" + parse[0] + "' AND password='"+ parse[1] +"' ;");
//					
//					//checks if we get any results from the SQL statement, if we haven't then the user doesn't exist, and the client have to try again
//					//rs should only contain one row if it was successfull
//					if(!rs.next())
//					{
//						System.out.println("NOTHING HERE");
//						pw.println("wrong");
//					}
//					else
//					{
//						System.out.println("User connected");
//						successLogin = true;
//						pw.println("accepted");
//					}
//					pw.flush();
//				}
				
				//end of login part
				
				//excpects a device name with a state  example  bulb:on
				System.out.println("Reading device");
				//BUG, will still read even after the client have disconnected
				String device = br.readLine();  //waiting for the user to write something (or push a button)
				String[] parse = device.split(":");  //split the devicename and state up
				
				System.out.println(device);
				
				System.out.println("Creating statement");
				
				//get the requested device and its current state
				Statement stmt = connect.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT deviceName,state FROM devices WHERE deviceName='" + parse[0] + "';");
				
				//point on the first row
				//rs.next();
					
					if(!rs.next())
					{
						sharedObject.sendInformationSoftware(clientID, parse[0] + ":" + parse[1]);
					
						//could not find the device
						System.out.println("Device not Found, sending to software");
						//sharedObject.sendInformationArd(clientID, parse[0] + ":" + parse[1]);
						//pw.println();
					}
					else
					{
						 
						if(rs.getString(2).equals(parse[1]))
						{
							//the state was not changed on the requested device
							System.out.println("Nothing happens");
							pw.println(parse[0] + " remains unchanged");
						}
						else
						{
							System.out.println(parse[0]);
							System.out.println(parse[1]);
							
						
							//update the state on the requested device, send back an OK to the client
							stmt.executeUpdate("UPDATE devices SET state='" + parse[1] + "' WHERE deviceName='" + parse[0] + "';");
							pw.println(parse[0] + " is " + parse[1]);
							
							
							//sharedObject.sendInformationSoftware(clientID, parse[0] + ":" + parse[1]);
							sharedObject.sendInformationArd(clientID, parse[0] + ":" + parse[1]);
						}
					}
					pw.flush();
					
					System.out.println("GOING ON");
			}
			
			pw.close();
			br.close();
		}
		catch(Exception ie)
		{
			ie.printStackTrace();
			try 
			{
				clientSocket.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public void readDatabase()
	{
		try 
		{
			//establish a connection the the database, username root and password 123
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/projectdb", "root", "123");
			Statement stmt = connect.createStatement();
			stmt.execute("USE projectdb");
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void executeStatement()
	{
	
	}
}
