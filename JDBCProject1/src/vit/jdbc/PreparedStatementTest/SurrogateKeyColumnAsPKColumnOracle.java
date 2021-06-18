//SurrogateKeyColumnAsPKColumnOracle.java
package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to generate PK column values in Oracle DB SW
   
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SurrogateKeyColumnAsPKColumnOracle {
	
	public static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(SNO_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) { 

		try(Scanner sc = new Scanner(System.in)){
		   String name=null,loc=null;
		   float avg=0.0f;
		   
		   if(sc!=null) {
			  //read inputs
			   System.out.print("Enter student name : ");
			   name=sc.nextLine().toUpperCase();
			   System.out.print("Enter student avg : ");
			   avg=sc.nextFloat(); sc.nextLine();
			   System.out.print("Enter student location : ");
			   loc=sc.nextLine().toUpperCase();
			   System.out.println();
		   }
		   
		   try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger")){
			   try(PreparedStatement ps=con.prepareStatement(INSERT_QUERY)){
				   //set params to PreparedStatement obj
				   if(ps!=null) {
					   ps.setString(1, name);
					   ps.setFloat(2, avg);
					   ps.setString(3, loc);
				   }
				   
				   //send and execute query
				   int count=0;
				   if(ps!=null)
					   count=ps.executeUpdate();
				   
				   if(count==0)
					   System.out.println("Record not inserted");
				   else
					   System.out.println("Record inserted");
			   }//try3
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