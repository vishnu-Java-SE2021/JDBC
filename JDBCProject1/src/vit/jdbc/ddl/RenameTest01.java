//RenameTable01.java

package vit.jdbc.ddl;

/*
  JDBC application to rename table in DB s/w  

 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RenameTest01 {

	public static void main(String[] args) {

		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			String tableName=null,newName=null;

			//read inputs
			sc=new Scanner(System.in);
		
			System.out.print("Enter table name to rename : ");
			tableName=sc.nextLine().toUpperCase();  // test1
			System.out.print("Enter new name : ");
			newName=sc.nextLine().toUpperCase();
			System.out.println();
			
			//prepare query to create table
			  //rename test1 to test01
			String query="RENAME "+tableName+" TO "+newName;
			
			//JDBC code
			//loading of driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
				
			//Establishing connection with DB s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			//creating statement obj
			if(con!=null)
				st=con.createStatement();
		  
			//send and execute query
			if(st!=null) {
				st.execute(query);
				//st.executeQuery(query);
				//st.executeUpdate(query);
				System.out.println("Table renamed.");
			}			
		
		}//try
		catch(SQLException se) {
				se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch

		finally {
			//closing JDBC obj's and stream obj	
			try {
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch

			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch

			try {
				if(sc!=null)
					sc.close();
			}//try
			catch(Exception e) {
				e.printStackTrace();
			}//catch
		}//finally	
	}//main
}//class
