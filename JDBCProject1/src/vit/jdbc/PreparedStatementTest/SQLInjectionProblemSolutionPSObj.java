package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to show solution for SQL injection problem by using 
   PreparedStatement obj.
   
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLInjectionProblemSolutionPSObj {
	public static final String LOGIN_QUERY="SELECT COUNT(*) FROM LOGIN_INFO WHERE USERNAME=? AND PASSWORD=?";
	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		try {
			//JDBC code
			//loading of driver class
			//Class.forName("jdbc.oracle.driver.OracleDriver");
			
			//Establishing connection with DB
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott", "tiger");
			
			//creating statement obj
			if(con!=null)
				ps=con.prepareStatement(LOGIN_QUERY);
			
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
			
			//set values to PreparedStatement obj
			if(ps!=null) {
				ps.setString(1,uname);
				ps.setString(2,pass);
			}
			
			//send and execute PreparedStatement obj in DB SW
			if(ps!=null) {
				rs=ps.executeQuery();
			}
			//process the ResultSet obj
			int count=0;
			if(rs!=null) {
				rs.next();
				count=rs.getInt(1);
			
			    if(count==1)
			    	System.out.println("Valid details");
			    else
			    	System.out.println("invalid details");		
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
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(ps!=null)
					ps.close();
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