package vit.jdbc.curd;

/*
JDBC application to perform CURD operations on student table in MySQL DB s/w
  CURD operations:
 =================
 ->C-create
 ->U-update
 ->R-read/select
 ->D-delete

 */

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCURDOps01 {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			//==================(C - create operation)========================
			//creating student table
			//user enters column names along with data types
			String column1=null,column2=null,column3=null,column4=null;
			String query=null;

			System.out.println("==========create table operation============");
			System.out.println();
			sc = new Scanner(System.in);
			if(sc!=null) {
				System.out.print("Enter column1 name with data type : ");
				column1=sc.nextLine().toUpperCase();
				System.out.print("Enter column2 name with data type : ");
				column2=sc.nextLine().toUpperCase();
				System.out.print("Enter column3 name with data type : ");
				column3=sc.nextLine().toUpperCase();
				System.out.print("Enter column4 name with data type : ");
				column4=sc.nextLine().toUpperCase();
				System.out.println();
			}

			//SQL query for creating table in MySQL DB
			//create table student(sno int,sname varchar(20),address varchar(20),avg float(4,2));		
			query="CREATE TABLE STUDENT("+column1+","+column2+","+column3+","+column4+")";
			System.out.println(query);
			System.out.println();

			//JDBC code
			//loading of driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");

			//Establishing connection with DB
			con = DriverManager.getConnection("jdbc:mysql:///projectdb","root","root");

			//creating statement obj
			if(con!=null)
				st=con.createStatement();

			//send and execute query in DB s/w 
			if(st!=null) {
				st.executeUpdate(query);
				System.out.println("table created");
				System.out.println();
			}

			//===============(U - insert,update operations)==================
			//===============( insert - operation)==================
			//reading inputs for inserting student records in student table
			int sno=0;
			String name=null,addrs=null;
			float avg=0.0f;
			int recordCount=0;

			System.out.println("==========insert operation============");
			System.out.println();

			System.out.print("How many records to you want to insert : ");
			recordCount=sc.nextInt();

			//if condition check, whether user entered postive integer or not
			if(recordCount>0) {
				for(int i=1;i<=recordCount;i++) {

					//performing insert operation on student table by reading inputs from user
					System.out.print("Enter student no. : ");
					sno=sc.nextInt();  sc.nextLine();   // 101
					System.out.print("Enter student name : ");
					name=sc.nextLine().toUpperCase();	// vishnu
					System.out.print("Enter student address : ");
					addrs=sc.nextLine().toUpperCase();	// hyd
					System.out.print("Enter student avg marks : ");
					avg=sc.nextFloat();					//75.8
					System.out.println();

					//converting strings to required SQL query form
					name="'"+name+"'";    //'VISHNU'
					addrs="'"+addrs+"'";  //'HYD'

					//SQL query for inserting records into student table
					//insert into student values(101,'vishnu','hyd',75.8);
					query="INSERT INTO STUDENT VALUES("+sno+","+name+","+addrs+","+avg+")";
					System.out.println(query);
					System.out.println();

					//send and execute query in DB s/w
					if(st!=null) {
						st.executeUpdate(query);
						System.out.println("record inserted");
						System.out.println();
					}		
				}//for
			}//if
			else {
				throw new Exception("passed wrong input");
			}

			//===============( update - operation)==================
			//read input for Updating student average marks in student table for given id no.

			System.out.println("==========update operation============");
			System.out.println();

			System.out.print("How many records to you want to update : ");
			recordCount=sc.nextInt();

			//if condition checks, whether user entered positive integer or not
			if(recordCount>0) {
				for(int i=1;i<=recordCount;i++) {

					System.out.print("Enter new avg marks : ");
					avg=sc.nextFloat();
					System.out.print("Enter student id no. : ");
					sno=sc.nextInt();
					System.out.println();

					//prepare SQL query
					//update student set avg=85 where sno=102;
					query="UPDATE STUDENT SET AVG="+avg+" WHERE SNO="+sno;
					System.out.println(query);
					System.out.println();
					if(st!=null) {
						st.executeUpdate(query);
						System.out.println("record updated");
						System.out.println();
					}
				}//for
			}//if
			else {
				throw new Exception("wrong input");
			}//else

			//===============(R - read/select operation)==================
			System.out.println("==========select operation============");
			System.out.println();

			//prepare SQL query
			//select * from student;
			query="SELECT * FROM STUDENT";
			if(st!=null) {
				recordCount=0;
				rs=st.executeQuery(query);
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					recordCount++;
				}
				System.out.println();
				System.out.println(recordCount+" records fetched");
				System.out.println();
			}//if

			//===============(D - delete operation)==================
			System.out.println("==========delete operation============");
			System.out.println();

			System.out.print("How many records to you want to delete : ");
			recordCount=sc.nextInt();

			//if condition checks, whether user entered positive integer or not
			if(recordCount>0) {
				for(int i=1;i<=recordCount;i++) {
					System.out.print("Enter student id no. : ");
					sno=sc.nextInt();
					System.out.println();

					//prepare SQL query
					//delete from student where sno=101;
					query="DELETE FROM STUDENT WHERE SNO="+sno;
					System.out.println(query);
					System.out.println();

					if(st!=null) {
						st.executeUpdate(query);
						System.out.println("record deleted");
						System.out.println();
					}
				}//for
			}//if
			else {
				throw new Exception("wrong input");
			}//else
			System.out.println("=============End of CURD operations============");
		}//try

		catch(SQLException se) {
			if(se.getErrorCode()>=900 || se.getErrorCode()<=999)
				se.printStackTrace();
			else
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
