//AlterTable01.java

package vit.jdbc.ddl;

/*
  JDBC application to alter table  in DB s/w based on user given alter sub-command  
  alter sub-commands:
   1)add
   2)modify
   3)rename
   4)drop

 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AlterTable01 {

	public static void main(String[] args) {

		Scanner sc=null;
		Connection con=null;
		Statement st=null;

		try {
			String tableName=null;
			String alterType=null;
			String query=null;

			//read inputs
			sc=new Scanner(System.in);

			if(sc!=null) {
				System.out.print("Enter table name to alter : ");
				tableName=sc.nextLine().toUpperCase();  // test
				System.out.print("Enter alter sub command : ");
				alterType=sc.nextLine().toUpperCase();  
				System.out.println();
			}

			//JDBC code
			//loading of driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			//Establishing connection with DB s/w
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");

			//creating statement obj
			if(con!=null)
				st=con.createStatement();

			//if user enters alter sub-command as 'add', it adds a column for the given table name 
			if(alterType.toUpperCase().equals("ADD")) {

				System.out.print("Enter column name with data type to add : ");
				String column=sc.nextLine().toUpperCase();
				System.out.println();

				//prepare query
				  //alter table test add(avg number(4,2));
				query="ALTER TABLE "+tableName+" "+alterType+"("+column+")";
				System.out.println(query);
				System.out.println();

				//send and execute query
				if(st!=null) {
					//st.execute(query);
					//st.executeQuery(query);
					st.executeUpdate(query);
					System.out.println("table altered.");
				}	
			}//if				

			//if user enters alter sub-command as 'modify', it modifies column of given table name 
			if(alterType.toUpperCase().equals("MODIFY")) {

				System.out.print("Enter column name with new data type to change : ");
				String column=sc.nextLine().toUpperCase();

				//prepare query
				  //alter table test add(avg number(4,2));
				query="ALTER TABLE "+tableName+" "+alterType+"("+column+")";
				System.out.println(query);
				System.out.println();

				if(st!=null) {
					//st.execute(query);
					//st.executeQuery(query);
					st.executeUpdate(query);
					System.out.println("table altered.");
				}	
			}//if

			//if user enters alter sub-command as 'rename', column name is changed with 
			//  new name for the given table name
			if(alterType.toUpperCase().equals("RENAME")) {

				System.out.print("Enter old column name to change : ");
				String oldColumn=sc.nextLine().toUpperCase();
				System.out.print("Enter new column name : ");
				String newColumn=sc.nextLine().toUpperCase();
				System.out.println();

				//prepare query
				  //alter table test add(avg number(4,2));
				query="ALTER TABLE "+tableName+" "+alterType+" COLUMN "+oldColumn+" TO "+newColumn;
				System.out.println(query);
				System.out.println();

				if(st!=null) {
					//st.execute(query);
					//st.executeQuery(query);
					st.executeUpdate(query);
					System.out.println("table altered.");
				}	
			}//if

			//if user enters alter sub-command as 'drop', particular column is dropped 
			// for the given table
			if(alterType.toUpperCase().equals("DROP")) {

				System.out.print("Enter column name to drop : ");
				String column=sc.nextLine().toUpperCase();
				System.out.println();

				//prepare query
   				  //alter table test drop column avg;
				query="ALTER TABLE "+tableName+" "+alterType+" COLUMN "+column;
				System.out.println(query);
				System.out.println();

				if(st!=null) {
					//st.execute(query);
					//st.executeQuery(query);
					st.executeUpdate(query);
					System.out.println("table altered.");
				}	
			}//if

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
