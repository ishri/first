package com.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	public static Connection getConnection() throws SQLException, IOException
	{

		Connection con=null;
		FileInputStream fis=new FileInputStream("D:\\JDBCProp.properties");
		Properties properties=new Properties();
		properties.load(fis);
		String driver=properties.getProperty("dbdriver");
		String url=properties.getProperty("dburl");
		String uid=properties.getProperty("dbuid");
		String pass=properties.getProperty("dbpassword");
		
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,uid,pass);
			System.out.println("Connection Successfull");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
				return con;
		
	}
	/*public static void main(String[] args) throws SQLException, IOException {
		ConnectionManager.getConnection();
	}*/
}
