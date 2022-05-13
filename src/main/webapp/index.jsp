<%@ page import="com.MeterReading" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meter Reading</title>

	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.4.1.min.js"></script>
	<script src="Components/MeterReading.js"></script>

</head>
<body>

	<div class="container"><div class="row"><div class="col-6">
	
	<h1>Meter Reading</h1>

	<form id="formItem" name="formItem" method="post" action="items.jsp">
 		User ID:
			<input id="UserID" name="UserID" type="text" class="form-control form-control-sm">
		<br> Full Name:
			<input id="FullName" name="FullName" type="text" class="form-control form-control-sm">
		<br> City:
			<input id="City" name="City" type="text" class="form-control form-control-sm">
		<br> Mobile Number:
			<input id="MobileNumber" name="MobileNumber" type="text" class="form-control form-control-sm">
		<br> Unit:
			<input id="Unit" name="Unit" type="text" class="form-control form-control-sm">
		<br>
		<br> Remark:
			<input id="Remark" name="Remark" type="text" class="form-control form-control-sm">
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
	</form>


				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
	<div id="divItemsGrid">
 		<%
 		MeterReading itemObj = new MeterReading();
 		out.print(itemObj.readItems());
 		%>
	</div>
	</div>
	</div>
	</div>

</body>
</html>