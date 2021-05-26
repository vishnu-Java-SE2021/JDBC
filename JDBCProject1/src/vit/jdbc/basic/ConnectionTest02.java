package vit.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest02 {

	public static void main(String[] args) throws Exception{
 
		//loading of driver class
		 //it automatically creates driver obj and register's with driverManager
		 // as part of static block logic.

	      Class.forName("oracle.jdbc.driver.OracleDriver");

		  //Class.forName("oracle.jdbc.OracleDriver");

		  //creating connection obj with database software
		  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");

	      if(con==null)
	          System.out.println("Connection is not established");
		  else
			  System.out.println("Connection is established");
	      System.out.println();
		  System.out.println("Connection obj class : "+con.getClass());

	}

}
