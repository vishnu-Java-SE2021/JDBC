package vit.jdbc.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest02 {

	public static void main(String[] args) throws Exception{
 
	    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");

		Statement st = con.createStatement();

	    Scanner sc = new Scanner(System.in);
		System.out.print("Enter start salary range : ");
		double startSal = sc.nextDouble();

	    System.out.println();

		System.out.print("Enter End salary range : ");
		double endSal = sc.nextDouble();
	    
		String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>"+startSal+" AND SAL<"+endSal;	

	    ResultSet rs = st.executeQuery(query);
	    System.out.println();
		System.out.println("Querying SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>"+startSal+" AND SAL<"+endSal);
		System.out.println("====================================================================================");

		while(rs.next()==true)
			System.out.println("'EMPNO' : "+rs.getString(1)+", 'ENAME' : "+rs.getString(2)+", 'JOB' : "+rs.getString(3)+", 'SAL' : "+rs.getString(4));
	    
		System.out.println();
		System.out.println("results fetched");
		System.out.println();
		
		rs.close();
		st.close();
		con.close();

		if(con==null)
			System.out.println("Connection is closed : "+con);
		else
			System.out.println("Connection is not closed : "+con);
		System.out.println();

	}

}
