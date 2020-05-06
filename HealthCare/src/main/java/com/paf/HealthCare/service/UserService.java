package com.paf.HealthCare.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {

	// Database Connection
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

		} catch (Exception e) {
			e.printStackTrace();

		}
		return con;
	}

	// Create an new User
	public String createUser(String firstName, String lastName, String userEmail, String userAddress, String idNumber,
			String telephoneNumber, String username, String password, String userAppointment) {

		String output = "";

		// Identify the user category by role code
		String roleCode = "4";

		try {
			Connection con = connect();

			if (con == null) {
				return "ERROR while connecting to the database for inserting";
			}

			// create a prepared statement
			String query = " insert into user (`FIRST_NAME`, `LAST_NAME`, `USER_EMAIL`, `USER_ADDRESS`, `ID_NUMBER`, `TELEPHONE_NUMBER`, `USER_USERNAME`, `USER_PASSWORD`, `USER_APPOINTMENT`, `ROLE_CODE`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pst = con.prepareStatement(query);

			// binding values
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setString(3, userEmail);
			pst.setString(4, userAddress);
			pst.setString(5, idNumber);
			pst.setString(6, telephoneNumber);
			pst.setString(7, username);
			pst.setString(8, password);
			pst.setString(9, userAppointment);
			pst.setString(10, roleCode);

			// execute the statement
			pst.execute();
			con.close();

			output = "Inserted successfully";
		}

		catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Get User details by identity number
	public String getUserById(String idNumber) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "ERROR while connecting to the database for inserting";
			}

			// create a prepared statement
			String query = "SELECT * FROM user WHERE ID_NUMBER =" + " '" + idNumber + "' ";

			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rst = pst.executeQuery(query);

			while (rst.next()) {

				String FirstName = rst.getString("FIRST_NAME");
				String LastName = rst.getString("LAST_NAME");
				String UserEmail = rst.getString("USER_EMAIL");
				String UserAddress = rst.getString("USER_ADDRESS");
				String IdNumber = rst.getString("ID_NUMBER");
				String TelephoneNumber = rst.getString("TELEPHONE_NUMBER");
				String Username = rst.getString("USER_USERNAME");
				String Password = rst.getString("USER_PASSWORD");
				String UserAppointments = rst.getString("USER_APPOINTMENT");

				output = FirstName + " & " + LastName;

			}

			// execute the statement
			con.close();
		}

		catch (Exception e) {
			output = "Error while Finding dataset.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Get total number of users
	public String getUserCount() {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "ERROR while connecting to the database for Counting";
			}

			// create a prepared statement
			String count = "SELECT COUNT(*) AS total FROM user";

			Statement pst = con.createStatement();
			ResultSet rstCount = pst.executeQuery(count);

			while (rstCount.next()) {

				String numberOfUsers = Integer.toString(rstCount.getInt("total"));
				output = numberOfUsers;

			}

			// Connection Close
			con.close();

		}

		catch (Exception e) {
			output = "Error while Counting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Update User details
	public String updateUser(String firstName, String lastName, String userEmail, String userAddress, String idNumber,
			String telephoneNumber, String username, String password, String userAppointment) {

		String output = "";

		try {

			Connection con = connect();

			if (con == null) {
				return "ERROR while connecting to the database for Updating.";
			}

			// create a prepared statement
			String updateQuery = "UPDATE user SET FIRST_NAME=?, LAST_NAME=?, USER_EMAIL=?, USER_ADDRESS=?, TELEPHONE_NUMBER=?, USER_USERNAME=?, USER_PASSWORD=?, USER_APPOINTMENT=? WHERE ID_NUMBER=?";
			PreparedStatement pst = con.prepareStatement(updateQuery);

			// Binding Values
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setString(3, userEmail);
			pst.setString(4, userAddress);
			pst.setString(5, telephoneNumber);
			pst.setString(6, username);
			pst.setString(7, password);
			pst.setString(8, userAppointment);
			pst.setString(9, idNumber);

			pst.executeUpdate();
			pst.close();
			con.close();

			output = "User Updated Successfully";

		} catch (Exception e) {

			output = "Error while updating the User.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	// Delete user by user id number
	public String deleteUser(String idNumber) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "ERROR - connecting to the database for Delete.";

			}
			// create a prepared statement
			String deleteQuery = "DELETE FROM user WHERE ID_NUMBER=" + " '" + idNumber + "' ";

			PreparedStatement pst = con.prepareStatement(deleteQuery);

			// execute the statement
			pst.execute();
			con.close();

			output = "Successfully Deleted";

		} catch (Exception e) {

			output = "Error while deleting the User.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
