//CreateTable01.java

package vit.jdbc.ddl;

/*
  JDBC application to create table in DB s/w  

 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateTable01 {

	public static void main(String[] args) {

		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			String tableName=null;
			String column1=null,column2=null,column3=null;

			//read inputs
			sc=new Scanner(System.in);
		
			System.out.print("Enter table name to create : ");
			tableName=sc.nextLine().toUpperCase();  // test
			System.out.print("Enter column1 name with data type : ");
			column1=sc.nextLine().toUpperCase();    // sno int
			System.out.print("Enter column2 name with data type : ");
			column2=sc.nextLine().toUpperCase();    // name varchar2(20)
			System.out.print("Enter column3 name with data type : ");
			column3=sc.nextLine().toUpperCase();    // address varchar2(40)
			System.out.println();
			
			//prepare query to create table
			  //create table Test(sid int,name varchar2(20),address varchar2(30));
			String query="CREATE TABLE "+tableName+"("+column1+","+column2+","+column3+")";
			
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
				System.out.println("table created.");
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
