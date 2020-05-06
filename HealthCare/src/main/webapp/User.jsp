<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.HospitalManagement.Hospital"%> 
<%@page import="com.HospitalManagement.HospitalRepository"%> 

<% ArrayList HospitalDetails =(ArrayList)request.getAttribute("HospitalDetails"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Componets/jquery-3.3.1.min.js"></script>
<script src="Componets/main.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-5">

				<h1 class="m-3">Hospital details</h1>

				<form id="formHospital" name="formHospital" method="post" action="Hospital.jsp">
							 Hospital Id:
							<input id="hospitalid" name="hospitalid" type="text"
							 class="form-control form-control-sm">
							<br> Hospital Name:
							<input id="name" name="name" type="text"
							 class="form-control form-control-sm">
							<br> Address:
							<input id="address" name="address" type="text"
							 class="form-control form-control-sm">
							<br> Hospital Charge:
							<input id="charge" name="charge" type="text"
							 class="form-control form-control-sm">
							 <br> Phone Number:
							<input id="phonenumber" name="phonenumber" type="text"
							 class="form-control form-control-sm">
							<br>Room Count:
							<input id="roomcount" name="roomcount" type="text"
							 class="form-control form-control-sm">
							 <br>
							<input id="btnSave" name="btnSave" type="button" value="Save"
							 class="btn btn-primary">
							<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
				<%
				//	HospitalRepository hospitalObj = new HospitalRepository();
				//	out.print(hospitalObj.viewHospital());
				%>
				<br>
			
				<table class="table table-dark">
					
				 	<tr>
					<th>Hospital ID</th>
					<th>Name</th>
					<th>Address</th>
					<th>Charge</th>
					<th>Phone Number</th>
					<th>Room Count</th>
					<th>Update</th>
					<th>Remove</th>
					</tr>
					 
					<% for(int i=0; i<HospitalDetails.size(); i++){ %>
					<% Hospital hos = (Hospital)HospitalDetails.get(i); %>
					 
								
						<tr>
						    <td><%=hos.getHospitalid()%></td>
							<td><%=hos.getName()%></td>
							<td><%=hos.getAddress()%></td>
							<td><%=hos.getCharge()%></td>
							<td><%=hos.getPhonenumber()%></td>
							<td><%=hos.getRoomcount()%></td>
							
						</tr>
						<%
						}
					%>
				</table>	
			
			
			</div>
		</div>
		
		<br>
		
		<div class="row">
					<div class="col-12" id="colHospital"></div>
		</div>
		
		
	</div>	

</body>
</html>