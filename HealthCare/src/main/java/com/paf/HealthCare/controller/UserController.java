package com.paf.HealthCare.controller;



import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.paf.HealthCare.service.UserService;

@Path("/user")
public class UserController {

	UserService userService = new UserService();

	// Test function
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String testing() {

		return "Test Function is working";

	}

	// Insert data
	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_HTML)
	public String createUser(

			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("userEmail") String userEmail, @FormParam("userAddress") String userAddress,
			@FormParam("idNumber") String idNumber, @FormParam("telephoneNumber") String telephoneNumber,
			@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("userAppointment") String userAppointment

	) {

		String output = userService.createUser(firstName, lastName, userEmail, userAddress, idNumber, telephoneNumber,
				username, password, userAppointment);
		return output;

	}

	// Find User by identity number
	@GET
	@Path("/id/{idNumber}")
	public String getUserById(@PathParam("idNumber") String idNumber) {

		return userService.getUserById(idNumber);
	}

	// Get User count
	@GET
	@Path("/count")
	public String getUserCount() {

		return userService.getUserCount();
	}

	// Update User details
	@PUT
	@Path("/update/{idNumber}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("userEmail") String userEmail, @FormParam("userAddress") String userAddress,
			@PathParam("idNumber") String idNumber, @FormParam("telephoneNumber") String telephoneNumber,
			@FormParam("username") String username, @FormParam("password") String password,
			@FormParam("userAppointment") String userAppointment) {

		return userService.updateUser(firstName, lastName, userEmail, userAddress, idNumber, telephoneNumber, username, password, userAppointment);
	}

	// Delete User from database
	@DELETE
	@Path("/delete/{idNumber}")
	public String deleteUser(@PathParam("idNumber") String idNumber) {

		return userService.deleteUser(idNumber);
	}

}
