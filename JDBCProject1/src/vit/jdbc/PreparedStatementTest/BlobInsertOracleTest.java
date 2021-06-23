//BlobInsertOracleTest.java
package vit.jdbc.PreparedStatementTest;

/*
  Java application to insert Binary large objects into Oracle DB SW
 */

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BlobInsertOracleTest {
	private static final String BLOB_INSERT="INSERT INTO MATRIMONY VALUES(MATRI_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		
		//Load JDBC Driver class
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		try(Scanner sc = new Scanner(System.in)){
			String name=null,dob=null;
			InputStream is=null;
			java.sql.Date sqdate=null;
			
			if(sc!=null) {
				//read inputs
				System.out.print("Enter name : ");
				name=sc.nextLine();
				System.out.print("Enter DOB(dd-MM-yyyy) : ");
				dob=sc.nextLine();
				System.out.print("Enter photo location : ");
				is=new FileInputStream(sc.nextLine());
				System.out.println();
			
				//converting String date to SQL date obj
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date ud = sdf.parse(dob);
				long ms=ud.getTime();
				sqdate = new java.sql.Date(ms);
			}//if
			
			//Establishing connection with Oracle DB SW
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger")){
				try(PreparedStatement ps = con.prepareStatement(BLOB_INSERT)){
				   
					//set parameters to PreparedStatement obj
					ps.setString(1, name);
					ps.setDate(2, sqdate);
					ps.setBinaryStream(3, is);
					
					//send and execute Query in DB
					int count=0;
					if(ps!=null)
						count=ps.executeUpdate();
					
					if(count==0)
						System.out.println("Record not inserted.");
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
