package com.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Statement;


public class CRUDOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CRUDOperation() {
        super();
           }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		Connection con=null;
		Statement st=null;
		String sql="";
		response.setContentType("text/html");
		try{
			con=ConnectionManager.getConnection();
			String createDB=request.getParameter("createDB");
			String createTable=request.getParameter("CreateTable");
			String insert=request.getParameter("insert");
			String update=request.getParameter("update");
			String delete=request.getParameter("delete");
			String read=request.getParameter("read");
			//st=con.createStatement();
			
			if(createDB.equals("CreateDB"))
			{
			//	con=ConnectionManager.getConnection();
                st=con.createStatement();
				sql="create database Company;";
				st.execute(sql);
				pw.println("Database created");
			}
			
			else if("CreateTable".equals(createTable))
			{
				//con=ConnectionManager.getConnection();

				st=con.createStatement();
				sql="create table If Not Exist EmployeeDetails.Emp(roll integer, name varchar(20), marks integer );";
				st.executeUpdate(sql);
				pw.println("Table created");
				
			}
			
			else if("Insert".equals(insert))
			{
				
				
				con=ConnectionManager.getConnection();

			 	st=con.createStatement();
				sql="insert into EmployeeDetails.Emp values(1, 'Ankita', 83);";
				st.executeUpdate(sql);
				pw.println("Data inserted");
				sql="insert into EmployeeDetails.Emp values(2, 'Ishita', 85);";
				st.executeUpdate(sql);
				pw.println("Data inserted");
				response.sendRedirect("Profile.html");
				sql="select * from EmployeeDetails.Emp;";
				ResultSet rs=st.executeQuery(sql);
				while(rs.next())
				{
					int roll=rs.getInt(1);
					String name=rs.getString(2);
					int marks=rs.getInt(3);
					pw.print(roll+" "+name+" "+marks);
				}
			}
			
			else if(update.equals("Update"))
			{
				//st=con.createStatement();
				sql="update EmployeeDetails.Emp set roll=3 where name='Ankita';";
				st.executeUpdate(sql);
				pw.println("Data updated");
			}
			
			else if(delete.equals("Delete"))
			{
				//st=con.createStatement();
				sql="Delete from EmployeeDetails.Emp where roll=2;";
				st.executeUpdate(sql);
				pw.println("Data deleted");
			}
	
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
