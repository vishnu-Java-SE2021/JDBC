package vit.jdbc.update;

/*
   JDBC application to insert employee details like empno,name,job,salary
    as given by the end user
    
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest03 {

	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			int empno=0;
			String name=null,job=null;
			float salary=0.0f;
			
			//read inputs
			sc=new Scanner(System.in);
			
			if(sc!=null) {
				System.out.print("Enter Employee no. : ");
				empno=sc.nextInt();   			// gives 101
				System.out.print("Enter Employee name : ");
				name=sc.next().toUpperCase();   // gives ntr
				System.out.print("Enter Employee job : ");
				job=sc.next().toUpperCase();    // gives manager
				System.out.print("Enter Employee salary : ");
				salary=sc.nextFloat();          // gives 20000
				System.out.println();
				
				//converting strings into required SQL query form
				name="'"+name+"'";    // gives 'NTR'
				job="'"+job+"'";      // gives  'MANAGER'
			}
			
			//prepare query
			  //insert into emp(empno,ename,job,sal) values(101,'ntr','manager',20000);
			String query="INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES("+empno+","+name+","+job+","+salary+")";
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
			
			//send and execute query in DB s/w 
			if(st!=null) {
			    st.executeUpdate(query);
			    System.out.println("record inserted");
			}
		
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 || se.getErrorCode()<=999)
			   se.printStackTrace();
			else
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