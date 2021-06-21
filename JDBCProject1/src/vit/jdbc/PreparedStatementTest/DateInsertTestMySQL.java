//DateInsertTestMySQL.java
package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to insert date values into MySQL DB SW using PreparedStatement object by taking 
   date values from end user in different formats.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertTestMySQL {
	private static final String JOBSEEKER_INSERT_QUERY="INSERT INTO JOB_SEEKER(JNAME,DOB,DOJ,DOM,LOC) VALUES(?,?,?,?,?)";
	public static void main(String[] args) { 

		try (Scanner sc=new Scanner(System.in)){
			String name=null,dob=null,doj=null,dom=null,loc=null;
            //read inputs
			if(sc!=null) {
				System.out.print("Enter job seeker name : ");
				name=sc.nextLine().toUpperCase();
				System.out.print("Enter job seeker DOB(dd-MM-yyyy) : ");
				dob=sc.nextLine();
				System.out.print("Enter job seeker DOJ(MMM-dd-yyyy) : ");
				doj=sc.nextLine();
				System.out.print("Enter job seeker DOM(yyyy-MM-dd) : ");
				dom=sc.nextLine();
				System.out.print("Enter job seeker location : ");
				loc=sc.nextLine().toUpperCase();
				System.out.println();
			}
			//converting DOB string date value to Oracle date format
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date ud1=sdf1.parse(dob);
			
			//converting DOB to java.sql.Date from java.util.Date class obj
			long ms1=ud1.getTime();
			java.sql.Date sd1 = new java.sql.Date(ms1);
			
			//Converting DOJ string value to Oracle date format
			SimpleDateFormat sdf2 = new SimpleDateFormat("MMM-dd-yyyy");
			java.util.Date ud2=sdf2.parse(doj);
			
			 //Converting DOJ to java.sql.Date from java.util.Date class obj
			long ms2=ud2.getTime();
			java.sql.Date sd2=new java.sql.Date(ms2);
			
			//Converting DOM value to java.sql.Date class obj using valueOf(-)
			java.sql.Date sd3 = java.sql.Date.valueOf(dom);
			
			try(Connection con = DriverManager.getConnection("jdbc:mysql:///projectdb","root","root")){
				
				if(con!=null) {
					try(PreparedStatement ps=con.prepareStatement(JOBSEEKER_INSERT_QUERY)){
						//set values to query parameters of PreparedStatement obj  
						if(ps!=null) {
							  ps.setString(1, name);
							  ps.setDate(2, sd1);
							  ps.setDate(3, sd2);
							  ps.setDate(4, sd3);
							  ps.setString(5, loc);
						  } 
						  
						  //send and execute query in DB SW
						  int count=0;
						  if(ps!=null && con!=null)
							  count=ps.executeUpdate();
						  
						  //process the result
						  if(count==0)
							  System.out.println("Record not inserted");
						  else
							  System.out.println("Record inserted");
					}//try3
				}//if				
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record insertion");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("problem in code");
		}
	}//main
}//class
