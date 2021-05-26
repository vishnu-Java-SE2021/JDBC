package vit.jdbc.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectTest01 {

	public static void main(String[] args) throws Exception{

		//Class.forName("oracle.jdbc.driver.OracleDriver"); =>optional

				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");

		        Statement st = con.createStatement();

				ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");
		        System.out.println("Querying SELECT * FROM STUDENT");
				System.out.println("===============================");

				while(rs.next()==true)  //(rs.next()!=false) (or) (rs.next())
				   
				   //recommended to use
				   System.out.println(rs.getInt(1)+" "+rs.getString(2).toUpperCase()+" "+rs.getDouble(3));
							//         (or)  
				  //System.out.println(rs.getInt("SNO")+" "+rs.getString("SNAME").toUpperCase()+" "+rs.getDouble("SAVG"));
							//			(or)
				  //System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
							//			(or)
				  //System.out.println(rs.getString("SNO")+" "+rs.getString("SNAME")+" "+rs.getString("SAVG"));

				System.out.println();
				System.out.println("Fetched all records");

	}

}
