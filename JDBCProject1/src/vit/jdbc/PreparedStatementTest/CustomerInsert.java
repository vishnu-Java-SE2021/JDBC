package vit.jdbc.PreparedStatementTest;

/*
   JDBC application to insert customer details into customer table 

 */

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerInsert {
	private static final String CUSTOMER_INSERT_QUERY="INSERT INTO CUSTOMER VALUES(?,?,?,?)";
	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			//JDBC code
			//loading of driver class
			//Class.forName("jdbc.oracle.driver.OracleDriver");
			
			//Establishing connection with DB
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott", "tiger");
			
			//creating statement obj
			if(con!=null)
				ps=con.prepareStatement(CUSTOMER_INSERT_QUERY);
			
			//read inputs
			sc=new Scanner(System.in);
			
			int count=0;
			System.out.print("Enter no. of customers to insert : ");
			count=sc.nextInt();
			System.out.println();
			
			int cno=0;
			String cname=null,addrs=null;
			float ctrade=0.0f;

			for(int i=1;i<=count;i++) {
				if(sc!=null) {
					System.out.print("Enter Customer id no. : ");
					cno=sc.nextInt();   			// gives 101
					System.out.print("Enter Customer name : ");
					cname=sc.next().toUpperCase();   // gives ntr
					System.out.print("Enter Customer address : ");
					addrs=sc.next().toUpperCase();    // gives manager
					System.out.print("Enter Customer purchase : ");
					ctrade=sc.nextFloat();          // gives 20000
					System.out.println();
				}//if
				ps.setInt(1,cno);
				ps.setString(2,cname);
				ps.setString(3,addrs);
				ps.setFloat(4,ctrade);
			
				//send and execute query in DB s/w 
				if(ps!=null && con!=null) {
					int result=ps.executeUpdate();
					
					if(result==1)
						System.out.println("record inserted");
					else
						System.out.println("record not inserted");
					System.out.println();
				}//if
			}//for
			
		}//try
		catch(SQLException se) {	
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch

		finally {
			//closing JDBC obj's and stream obj	
			try {
				if(ps!=null)
					ps.close();
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