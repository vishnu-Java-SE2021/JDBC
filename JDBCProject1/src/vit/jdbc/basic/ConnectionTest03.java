package vit.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest03 {

	public static void main(String[] args) throws Exception{

		/*In ojdbc14.jar->Type-3 mechanism, it doesnt supprts autoloading of 
		   Driver class,because it doesn't contain META-INF/Services folder 
		   to load in jar file.

	     =>Autoloading can be done by type-4 mechanism
		   autoloading of driver class is done by ojdbc6.jar which
		   Driver class name in META-INF/Services folder in ojdbc6.jar file. 

		  So, DriverManager class loades that Driver class through static block which
		   inturns executes static block of Driver class to create obj of it and register
		   with DriverManager service.
	     */

	    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");

	    if(con==null)
			System.out.println("Connection is not established");
	    else
			System.out.println("Connection is established");

		System.out.println();

	}

}
