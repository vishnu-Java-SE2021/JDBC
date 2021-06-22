//AgeCalculatorOracle.java
package vit.jdbc.PreparedStatementTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
   JDBC application to calculate age by providing certain details to Oracle DB SW
   
   QUERY : SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM TABLE_NAME WHERE ID=?
*/



public class AgeCalculatorOracle {
	private static final String AGE_RETREIVE_QUERY="SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM FAMILY_DETAILS WHERE PID=?";
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
					try(PreparedStatement ps=con.prepareStatement(AGE_RETREIVE_QUERY)){
						//set values to query parameters of PreparedStatement obj  
						if(ps!=null) {
							  ps.setInt(1, id);
						  } 
						  
						  //send and execute query in DB SW
						try(ResultSet rs=ps.executeQuery()){
							if(rs.next())
								System.out.println(" AGE : "+rs.getFloat(1));
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
