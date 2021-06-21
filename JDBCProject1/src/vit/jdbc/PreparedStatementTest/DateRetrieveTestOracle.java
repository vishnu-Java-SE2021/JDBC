//DateRetrieveTestOracle.java
package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to retrieve date values from Oracle DB SW 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetrieveTestOracle {
	private static final String JOBSEEKER_DATES_RETRIEVE_QUERY="SELECT JNAME,DOB,DOJ,DOM,LOC FROM JOB_SEEKER WHERE JSID=?";
	public static void main(String[] args) { 

		try (Scanner sc=new Scanner(System.in)){
			int jsid=0;
            //read inputs
			if(sc!=null) {
				System.out.print("Enter job seeker id : ");
				jsid=sc.nextInt();
			}
			
			//Load JDBC Driver class
			//Class.forName("Oracle.jdbc.Driver.OracleDriver");
			
			//Establishing Connection with Oracle DB SW
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger")){
				
				//creating PreparedStatement obj
				if(con!=null) {
					try(PreparedStatement ps=con.prepareStatement(JOBSEEKER_DATES_RETRIEVE_QUERY)){
						//set values to query parameters of PreparedStatement obj  
						if(ps!=null) 
							  ps.setInt(1, jsid);
						  
					    //send and execute query in DB SW
						ResultSet rs = ps.executeQuery();
						
		    			//process the result
						if(rs!=null) {
				     		while(rs.next()) {
					   		    System.out.println("Name : "+rs.getString(1)+"\n"+"DOB(SQL) : "+rs.getDate(2)+"\n"+
				     		       "DOJ(SQL) : "+rs.getDate(3)+"\n"+"DOM(SQL) : "+rs.getDate(4)+"\n"+"LOC : "+rs.getString(5));
				     		    
					   		    System.out.println();
					   		    System.out.println("Name : "+rs.getString(1));
				     		   
				     		    java.sql.Date sdob = rs.getDate(2);
				     		    java.sql.Date sdoj = rs.getDate(3);
				     		    java.sql.Date sdom = rs.getDate(4);
				     		  
				     		    SimpleDateFormat sd1=new SimpleDateFormat("dd-MM-yyyy");
				     		    String dob=sd1.format(sdob);
				     		    System.out.println("DOB(dd-MM-yyyy) : "+dob);
				     		    
				     		    SimpleDateFormat sd2=new SimpleDateFormat("MMM-dd-yyyy");
				     		    String doj=sd2.format(sdoj);
				     		    System.out.println("DOJ(MMM-dd-yyyy) : "+doj);
	 			     		    
				     		    SimpleDateFormat sd3=new SimpleDateFormat("yyyy-MM-dd");
				     		    String dom=sd3.format(sdom);
				     		    System.out.println("DOM(yyyy-MM-dd) : "+dom);
				     		    
				     		    System.out.print("LOC : "+rs.getString(5));
				     		}//while
						}//if
					}//try4
				}//if
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
