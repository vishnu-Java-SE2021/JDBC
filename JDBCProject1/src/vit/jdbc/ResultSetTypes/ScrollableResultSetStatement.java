//ScrollableResultSetStatement 

package vit.jdbc.ResultSetTypes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableResultSetStatement {
	public static final String SELECT_QUERY="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	public static void main(String[] args) {
		
		//creating connection, Statement obj and ResultSet obj
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery(SELECT_QUERY)){
			
			//fetching records
			if(rs!=null) {
				while(rs.next()) 
					System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			}
			System.out.println("========================================================================");
			
			if(rs!=null) {
				rs.afterLast();
				while(rs.previous())
					System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			}
			System.out.println("========================================================================");
			
			rs.first();
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			rs.last();
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			rs.previous();
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			rs.next();
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			
			System.out.println(rs.isLast());
			System.out.println(rs.isFirst());
			rs.next();
			System.out.println(rs.isAfterLast());
			System.out.println(rs.isBeforeFirst());
			
			System.out.println("========================================================================");
			
			rs.first();
			rs.relative(5);
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			rs.relative(-3);
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			
			rs.absolute(12);
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			rs.absolute(-3);
			System.out.println(rs.getRow()+"==>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
