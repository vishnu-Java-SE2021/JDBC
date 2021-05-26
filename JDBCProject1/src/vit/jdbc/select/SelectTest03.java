package vit.jdbc.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest03 {

	public static void main(String[] args) throws Exception{
 
		//Class.forName("jdbc.oracle.driver.OracleDriver");

		  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger") ;

	      Statement st = con.createStatement();

	      Scanner sc = new Scanner(System.in);
		
		  System.out.print("Enter min avg : ");
		  double minAvg = sc.nextDouble();
		  System.out.println();

	      System.out.print("Enter max avg : ");
		  double maxAvg = sc.nextDouble();
		  System.out.println();

		  String query="SELECT SNO,SNAME,SAVG FROM STUDENT WHERE SAVG>="+minAvg+" AND SAVG<="+maxAvg;

	      ResultSet rs = st.executeQuery(query);
		  System.out.println("Querying SELECT SNO,SNAME,SAVG FROM STUDENT WHERE SAVG>="+minAvg+" AND SAVG<="+maxAvg);
		  System.out.println("============================================================================");

		  while(rs.next()==true)
			  System.out.println("'SNO' : "+rs.getInt(1)+", 'SNAME' : "+rs.getString(2).toUpperCase()+", 'AVG' : "+rs.getDouble(3));
		  System.out.println();
	     
	////////////////////////////////////////////////////////////////////////////////////////
	     
		  
		  System.out.print("Enter start deptno : ");
		  int minNum = sc.nextInt();
		  System.out.println();

	      System.out.print("Enter end deptno : ");
		  int maxNum = sc.nextInt();
		  System.out.println();

		  String query1="SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO>="+minNum+" AND DEPTNO<="+maxNum;

	      ResultSet rs1 = st.executeQuery(query);
		  System.out.println("Querying SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO>="+minNum+" AND DEPTNO<="+maxNum);
		  System.out.println("===========================================================================");

		  while(rs1.next()==true)
			  System.out.println("'DEPTNO' : "+rs1.getInt(1)+", 'DNAME' : "+rs1.getString(2).toUpperCase()+", 'LOC' : "+rs1.getString(3));
		
	      System.out.println();
		  System.out.println("records fetched...");
	      System.out.println();
	   
		  rs.close();
		  st.close();
		  con.close();

	}

}
