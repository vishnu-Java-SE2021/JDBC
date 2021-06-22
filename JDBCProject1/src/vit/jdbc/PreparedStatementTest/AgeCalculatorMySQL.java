//AgeCalculatorMySQL.java
package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to calculate age by providing certain details to MySQL DB SW
   
   QUERY : SELECT TIMESTAMPDIFF(DAY,DOB,CURDATE())/365.25 FROM FAMILY_DETAILS WHERE PID=?
   								(or)
   QUERY : SELECT TIMESTAMPDIFF(YEAR,DOB,CURDATE()) FROM FAMILY_DETAILS WHERE PID=?
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AgeCalculatorMySQL {
	private static final String AGE_RETREIVE_QUERY="SELECT TIMESTAMPDIFF(YEAR,DOB,CURDATE()) FROM FAMILY_DETAILS WHERE PID=?";
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
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establishing connection with MySQL DB SW
			try(Connection con = DriverManager.getConnection("jdbc:mysql:///projectdb","root","root")){
				
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
