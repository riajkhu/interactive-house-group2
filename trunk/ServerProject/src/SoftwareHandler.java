import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SoftwareHandler extends Thread
{
	
	private Socket clientSocket;
	private InputStreamReader isr;
	private OutputStream os;
	private Connection connect;
	
	private SharedObject sharedObject;
	private int clientID;
	
	public SoftwareHandler(Socket clientSocket,SharedObject sharedObject,int clientID)
	{
		this.clientSocket = clientSocket;
		this.sharedObject = sharedObject;
		this.clientID = clientID;
		
		sharedObject.addSoftwareClient(clientSocket, clientID);
	}
	public void run(){
		readDatabase();
		try {
			isr=new InputStreamReader(clientSocket.getInputStream());
			os=clientSocket.getOutputStream();
			
			BufferedReader br=new BufferedReader(isr);
			PrintWriter pw=new PrintWriter(os,true);
			
			//pw.println("request");
			//String deviceStates=br.readLine();
			
			String deviceStates = "coffee:off,music:off";
			
			//updateDeviceStates(deviceStates);
			
			while(!clientSocket.isClosed())
			{
				String unit=br.readLine();
				System.out.println("CMON  " + unit);
				
				
//				String parse[]=unit.split(":");
//			
//				System.out.println(parse[0]);
				//Statement st=connect.createStatement();
				//st.execute("UPDATE devices SET state='" + parse[1] + "' WHERE deviceName='" + parse[0] + "';");
				//pw.println(unit);
			}
		} catch (IOException e) { //| SQLException e
			e.printStackTrace();
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
	
	public void updateDeviceStates(String deviceStates)//throws SQLException
	{
		
		//String[] devices = deviceStates.split(",");
		
		
		
//		Statement st=connect.createStatement();
//		
//		for(int i =0; i < devices.length; i++)
//		{
//			String[] update = devices[i].split(":");
//			
//			st.execute("UPDATE devices SET state='" + update[1] + "' WHERE deviceName='" + update[0] + "';");
//		}
	}

}
 