package vit.jdbc.update;

/*
   JDBC application to increase the salary of employee based on given hike percentage on
   base salary.
 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest01 {

	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {		
			int perHike=0;	
			int startSalRange=0;
			int endSalRange=0;
			String query=null;
			int count=0;
			
			//reading input
			sc=new Scanner(System.in);
			System.out.print("Enter percentage for providing hike : ");
			perHike=sc.nextInt();  //gives 10
			System.out.print("Enter start salary range : ");
			startSalRange=sc.nextInt();  //gives 1500
			System.out.print("Enter start salary range : ");
			endSalRange=sc.nextInt();  //gives 3000
			System.out.println();
			
			//sample query
			  //update emp set sal=sal+((sal/100)*10) where sal>=1500 and sal<=3000;
			  //above query increases employee salary by 10% on existing salary
			query="UPDATE EMP SET SAL=SAL+((SAL/100)*"+perHike+") WHERE SAL>="+startSalRange+" AND SAL<="+endSalRange;
			System.out.println(query);
			System.out.println();
			
			//JDBC code
			//loading of driver class
		    //Class.forName("jdbc.oracle.driver.OracleDriver");
		
		    //Establishing connection with DB
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott", "tiger");
		
			//creating statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			
			//executing query and displaying no. of rows updated
			if(st!=null) {
			    count=st.executeUpdate(query);
				System.out.println(count+" rows updated.");
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
