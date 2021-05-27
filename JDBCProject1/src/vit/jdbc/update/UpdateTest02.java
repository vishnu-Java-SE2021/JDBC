package vit.jdbc.update;

/*
   JDBC application to increase student average marks for given percentage based 
    on user given input.
    
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest02 {

	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			int perMarks=0;  //perMarks represents that how much percentage of marks should be added
			String city1=null;
			String city2=null;
			String city3=null;
			String query=null;
			int count=0;
			
			//reading inputs
			sc=new Scanner(System.in);
			System.out.print("Enter how much percentage of marks to increase : ");
			perMarks=sc.nextInt();
			
			sc.nextLine();   //to clear scanner obj
			
			System.out.print("Enter city1 name : ");
			city1=sc.nextLine().toUpperCase();   //HYDERABAD
			System.out.print("Enter city2 name : ");
			city2=sc.nextLine().toUpperCase();   //PUNE
			System.out.print("Enter city3 name : ");
			city3=sc.nextLine().toUpperCase();   //MUMBAI
			System.out.println();
			
			//conversion of city names as per SQL query
			city1="'"+city1+"'";   //gives 'HYDERABAD'
			city2="'"+city2+"'";   //gives 'PUNE'
			city3="'"+city3+"'";   //gives 'MUMBAI'
			
			//sample query
			  //UPDATE STUDENT SET SAVG = SAVG + ((SAVG/100)*10) WHERE LOC IN('HYDERABAD','PUNE','MUMBAI');
			query="UPDATE STUDENT SET SAVG = SAVG + ((SAVG/100)*"+perMarks+") WHERE LOC IN("+city1+","+city2+","+city3+")";
			System.out.println(query);
			System.out.println();
			
			//JDBC code
			//loading of driver class
		    //Class.forName("jdbc.oracle.driver.OracleDriver");
		
		    //Establishing connection with DB
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott", "tiger");
		
			//creating statement obj
			if(con!=null) {
				st=con.createStatement();
			}
			
			//executing query and displaying no. of rows updated
			if(st!=null) {
			    count=st.executeUpdate(query);
				System.out.println(count+" rows updated.");
			}	
		
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
				if(st!=null)
					st.close();
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
