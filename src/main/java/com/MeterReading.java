package com;

import java.sql.*;

public class MeterReading {
	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");

		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project2022_sliit", "root", "");
		}
		 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	//insert into database
	public String insertmeterunit(String UserID, String FullName, String City, String MobileNumber, String Unit, String Remark)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
				
				// create a prepared statement
				String query = " insert into meterunit (`id`,`UserID`,`FullName`,`City`,`MobileNumber`,`Unit`,`Remark`)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";
			 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, UserID);
				preparedStmt.setString(3, FullName);
				preparedStmt.setString(4, City);
				preparedStmt.setString(5, MobileNumber);
				preparedStmt.setString(6, Unit);
				preparedStmt.setString(7, Remark);

			 

				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newItems = readItems();
				 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			 
			}
		 
		 
		 	catch (Exception e)
		 	{
		 
		 		output = "{\"status\":\"error\", \"data\": \"Error while inserting the meterreading.\"}";
		 		System.err.println(e.getMessage());
		 	}
		 	return output;
	}
	
	//read data from database.
	public String readItems()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr>"
						+"<th>UserID</th>"
						+ "<th>FullName</th>"
						+"<th>City</th>"
						+"<th>MobileNumber</th>"
						+"<th>Unit</th>"
						+"<th>Remark</th>"
						+"<th>Update</th><th>Remove</th></tr>";


			// create a prepared statement
			String query = "select * from meterunit";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				//get data from database and assign to local variables.
				String id = Integer.toString(rs.getInt("id"));
				String UserID = rs.getString("UserID");
				String FullName = rs.getString("FullName");
				String City = rs.getString("City");
				String MobileNumber = rs.getString("MobileNumber");
				String Unit = rs.getString("Unit");
				String Remark = rs.getString("Remark");

				// Add into the html table
				output += "<tr><td><input id=\'hiduseridUpdate\' UserID=\'hiduseridUpdate\' type=\'hidden\' value=\'" + id + "'>'" + UserID + "</td>";
				output += "<td>" + FullName + "</td>";
				output += "<td>" + City + "</td>";
				output += "<td>" + MobileNumber + "</td>";
				output += "<td>" + Unit + "</td>";
				output += "<td>" + Remark + "</td>";
		 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='"
						+ id + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Update
	public String updatemeterunit(String id, String UserID, String FullName, String City, String MobileNumber, String Unit, String Remark)
	{
		String output = "";
		try
		{
			
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for updating."; 
				}
			 
				// create a prepared statement
				String query = "UPDATE meterunit SET UserID=?,FullName=?,City=?,MobileNumber=?,Unit=?,Remark=? WHERE id=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, UserID);
				preparedStmt.setString(2, FullName);
				preparedStmt.setString(3, City);
				preparedStmt.setString(4, MobileNumber);
				preparedStmt.setString(5, Unit);
				preparedStmt.setString(6, Remark);
				preparedStmt.setInt(7, Integer.parseInt(id));
			 
			
				// execute the statement
				preparedStmt.execute();
				con.close();

				String newItems = readItems();
				output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the meterreading.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
			
		//Delete
		public String deletemeterunit(String id)
			 {
			 	String output = "";
			 	try
			 	{
			 		Connection con = connect();
			 		if (con == null)
			 		{
			 			return "Error while connecting to the database for deleting."; 
			 		}
			 
			 		// create a prepared statement
			 		String query = "delete from meterunit where id=?";
			 		PreparedStatement preparedStmt = con.prepareStatement(query);
			 		
			 		// binding values
			 		preparedStmt.setInt(1, Integer.parseInt(id));
			 		
			 		// execute the statement
			 		preparedStmt.execute();
			 		con.close();

			 		String newItems = readItems();
					output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			 		
			 	}
			 	catch (Exception e)
			 	{
			 		output = "{\"status\":\"error\", \"data\": \"Error while delete the item.\"}";
			 		System.err.println(e.getMessage());
			 	}
			 	return output;
			 }

}