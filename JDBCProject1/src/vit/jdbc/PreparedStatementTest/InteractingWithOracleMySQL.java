//InteractingWithOracleMySQL.java
package vit.jdbc.PreparedStatementTest;

/*
  JDBC application to interact with Oracle and MySQL DB SW's to copy data from Oracle to MySQL
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InteractingWithOracleMySQL {
	private static final String ORACLE_SELECT="SELECT SNAME,LOC,SAVG FROM STUDENT";
	private static final String MySQL_INSERT="INSERT INTO STUDENT(SNAME,SADD,SAVG) VALUES(?,?,?)";
	
	public static void main(String[] args) {

		//Load JDBC Driver class
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		//Establishing connections with Oracle and MySQL DB SW's
		try(Connection oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
				Connection mysqlCon = DriverManager.getConnection("jdbc:mysql:///projectdb","root","root")){

			//Creating Statement obj for Select Query of Oracle DB
			//Creating PreparedStatement obj for Insert Query of MySQL DB
			try(Statement st=oraCon.createStatement();
					PreparedStatement ps=mysqlCon.prepareStatement(MySQL_INSERT)){

				//send and execute Select Query in Oracle DB
				try(ResultSet rs = st.executeQuery(ORACLE_SELECT)){

					//process the Results and set parameters to PreparedStatement obj
					while(rs.next()) {
						ps.setString(1, rs.getString(1));
						ps.setString(2, rs.getString(2));
						ps.setFloat(3, rs.getFloat(3));

						//execute PreparedStatement obj
						int count=ps.executeUpdate();
						if(count==1)
							System.out.println("record inserted.");
						else
							System.out.println("record not inserted.");
					}//while
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
