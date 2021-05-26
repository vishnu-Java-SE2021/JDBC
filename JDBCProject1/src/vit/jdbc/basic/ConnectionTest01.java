package vit.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest01 {

	public static void main(String[] args) throws Exception{
		
		//creating driver class obj	
		   oracle.jdbc.driver.OracleDriver obj = new oracle.jdbc.driver.OracleDriver();
		   
		   //registering with driver manager service
		   DriverManager.registerDriver(obj);

		   //establishing connection with DB s/w
		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");

		   if(con==null)
			   System.out.println("Conn is not established");
		   else
			   System.out.println("Conn is established");

	}

}
