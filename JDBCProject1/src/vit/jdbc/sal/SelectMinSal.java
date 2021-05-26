package vit.jdbc.sal;

/*
   Java application to interact with database and fetch employee details whose salary
   is minimum in emp table
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMinSal {
	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		
		try {
		 //loading of driver class
		 //Class.forName("jdbc.oracle.driver.OracleDriver");
		
		 //Establishing connection with DB
		 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott", "tiger");
		
         //creating statement obj
		 if(con!=null)
			st=con.createStatement();
		
		 //prepare query
		 //select empno,ename,job,sal from emp where sal=(select min(sal) from emp);
		 query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
		 System.out.println("Query : "+query);
		 System.out.println();
		 
		 if(st!=null) {
			rs=st.executeQuery(query);		
		  while(rs.next())
			  System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		  }//if
	 	 }//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		
		finally {
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
        }//finally	
	}//main		
}//class
