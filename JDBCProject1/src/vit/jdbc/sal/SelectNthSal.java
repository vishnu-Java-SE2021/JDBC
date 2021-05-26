package vit.jdbc.sal;

/*
   Java application to interact with database and fetch employee details of nth highest
   salary from emp table
 
 */

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNthSal {
	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		Scanner sc=null;
		int nthHighestSal=0;   //to store nth highest number from end user
		boolean status=true;  //to make sure that user enters positive value, if entered 
		                      // negative number, it becomes false and query won't execute
		
		try {
			//reading input
			sc=new Scanner(System.in);
			
			if(sc!=null) {
				System.out.print("Enter Nth highest salary :: ");
				nthHighestSal=sc.nextInt();
			}
			
			//Sample query to fetch highest salary from emp table shown below
			//query="SELECT * FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			
			query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP";
			
			if(nthHighestSal>0) {
				if(nthHighestSal==1) {
					query=query+")";
				}//inner if condition
			
				//preparing nthHighest salary query 
				else{
					for(int i=1;i<nthHighestSal;i++) 
						query=query+" WHERE SAL<(SELECT MAX(SAL) FROM EMP";
					for(int i=1;i<=nthHighestSal;i++)
						query=query+")";
					
						//query prepared and displaying
						System.out.println();
						System.out.println("Query :: "+query);
						System.out.println();
					}//inner else
			}else {
				System.out.println("Wrong input!!..");
				status=false;
			}
			
			
			//**JDBC code**
			
		    //*loading of driver class
		    //Class.forName("jdbc.oracle.driver.OracleDriver");
		
		    //*Establishing connection with DB
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott", "tiger");
		
			//*creating statement obj
			if(con!=null) {
				st=con.createStatement();
			}

			//*executing query and displaying records
			if(st!=null) {
				if(status==true) {
					rs=st.executeQuery(query);
					System.out.println("Employee with "+nthHighestSal+" highest sal in emp table");
					System.out.println("=====================================================");
					while(rs.next())
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));			
				}//inner if condition
			}

		}//try

		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		
		finally {
			//closing all JDBC obj's and stream obj
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
