package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to show SQL injection problem if we use Simple statement obj
   for login details.
    Ex 1:  username : xyz' --
    	   password : 123         valid details  (wrong results)
    
    Ex 2:  username : xyz' -- OR 1=1
    	   password : obj  		  valid details   (wrong results)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionProblemSTObj {
	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs=null;

		try {
			//read inputs
			sc=new Scanner(System.in);
			String uname=null,pass=null;
			
			if(sc!=null) {
				System.out.print("Enter username : ");
				uname=sc.nextLine();                 
				System.out.print("Enter password : ");
				pass=sc.next();
				System.out.println();
			}
			
			//converting inputs as per sql query
			uname="'"+uname+"'";
			pass="'"+pass+"'";
			
			String query="SELECT COUNT(*) FROM LOGIN_INFO WHERE USERNAME="+uname+" AND PASSWORD="+pass;
			System.out.println(query);
			System.out.println();
			
			//JDBC code
			//loading of driver class
			//Class.forName("jdbc.oracle.driver.OracleDriver");
			
			//Establishing connection with DB
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott", "tiger");
			
			//creating statement obj
			if(con!=null)
				st=con.createStatement();
			
			if(st!=null)
				rs=st.executeQuery(query);
			
			if(rs.next())
				System.out.println("Valid details");
			else
				System.out.println("invalid details");		
			
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
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
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