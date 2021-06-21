//DateRetrieveInGivenRangeTest.java
package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to retrieve date values from Oracle DB SW 
*/

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetrieveInGivenRangeOracleTest {
	private static final String JOBSEEKER_DATES_RETRIEVE_QUERY="SELECT ENAME,JOB,HIREDATE FROM EMP WHERE HIREDATE>=? AND HIREDATE<=?";
	public static void main(String[] args) { 

		try (Scanner sc=new Scanner(System.in)){
			String sdate=null,edate=null;
            //read inputs
			if(sc!=null) {
				System.out.print("Enter EMP Start HireDate(dd-MM-yyyy) : ");
				sdate=sc.nextLine();
				System.out.print("Enter EMP End HireDate(dd-MM-yyyy) : ");
				edate=sc.nextLine();
				System.out.println();
			}
			
			//converting String date values to sql date class obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Date sqsdate=new java.sql.Date(sdf.parse(sdate).getTime());
			java.sql.Date sqedate=new java.sql.Date(sdf.parse(edate).getTime());
			
			//Load JDBC Driver class
			//Class.forName("Oracle.jdbc.Driver.OracleDriver");
			
			//Establishing Connection with Oracle DB SW
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger")){
								
				//creating PreparedStatement obj
				if(con!=null) {
					try(PreparedStatement ps=con.prepareStatement(JOBSEEKER_DATES_RETRIEVE_QUERY)){
						//set values to query parameters of PreparedStatement obj  
						if(ps!=null) {
							  ps.setDate(1, sqsdate);
							  ps.setDate(2, sqedate);
						}
								  
					    //send and execute query in DB SW
						try(ResultSet rs = ps.executeQuery()){
						
							//process the result
							if(rs!=null) {
								while(rs.next()) {
									System.out.println("Name : "+rs.getString(1)+", JOB : "+rs.getString(2)+", HIREDATE : "+rs.getDate(3));

									System.out.println("--------------------------------------------------------");
									System.out.println("Name : "+rs.getString(1));

									System.out.println("JOB : "+rs.getString(2));

									java.sql.Date sqlHireDate = rs.getDate(3);
									
									SimpleDateFormat sd1=new SimpleDateFormat("dd-MM-yyyy");
									String hireDate=sd1.format(sqlHireDate);
									System.out.println("DOB(dd-MM-yyyy) : "+hireDate);
									System.out.println();
								}//while
							}//if
						}//try4
					}//try3
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
