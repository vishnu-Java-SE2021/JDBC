//AgeCalculatorManuallyJavaCode.java
package vit.jdbc.PreparedStatementTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

/*
   JDBC application to calculate age by taking DOB from Oracle DB SW through java code
   
*/

public class AgeCalculatorManuallyJavaCode {
	private static final String DOB_RETREIVE_QUERY="SELECT DOB FROM FAMILY_DETAILS WHERE PID=?";
	public static void main(String[] args) { 

		try (Scanner sc=new Scanner(System.in)){
			int id=0;
            //read inputs
			if(sc!=null) {
				System.out.print("Enter id : ");
				id=sc.nextInt();
				System.out.println();
			}
			
			//Load JDBC Driver class
			//Class.forName("Oracle.jdbc.Driver.OracleDriver");
			
			//Establishing connection with MySQL DB SW
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger")){
				
				//Creating PreparedStatement obj
				if(con!=null) {
					try(PreparedStatement ps=con.prepareStatement(DOB_RETREIVE_QUERY)){
						//set values to query parameters of PreparedStatement obj  
						if(ps!=null) {
							  ps.setInt(1, id);
						  } 
						  
						//send and execute query in DB SW
						try(ResultSet rs=ps.executeQuery()){
							if(rs.next()) {
								java.sql.Date sdob=rs.getDate(1);

								//create current date obj
								java.util.Date sysdate = new java.util.Date();
								
								//calculate age from dob to current date using below formulae
								float age = (sysdate.getTime()-sdob.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.250f);

								/* DecimalFormat df = new DecimalFormat(); 
								  df.setMaximumFractionDigits(2); */
								
								//format the age as per requirement 
								DecimalFormat df = new DecimalFormat("#.##");
								System.out.println(df.format(age));
								
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
