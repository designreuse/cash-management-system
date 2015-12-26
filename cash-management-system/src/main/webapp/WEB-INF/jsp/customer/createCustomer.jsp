<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.css" />
<title>Add a new customer</title>
</head>
<body>
	<jsp:include page="../fragments/header.jsp"/>

	<div class="container-fluid">
	<div class="row">
	<div class="col-md-12 col-xs-12">
	<br>
	<a href="<c:url value="/customer"/>" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> Back</a>
	</div>
	</div>
	</div>
	
	<div class="container-fluid text-center">
	<div class="row">
	<div class="col-md-12 col-xs-12">
	<h2 style="color: #337ab7;font-family: 'Helvetica Neue'">Add a new customer</h2>
	<c:url var="post_url"  value="/customer/put" />
	<form action="${post_url}" method="POST" class="form-horizontal" role="form">
		<table style="margin: 0 auto !important; float: none !important">
			<tr>
				<td><label class="control-label col-sm-2">Name:</label></td>
				<td><input type="text" name="name" class="form-control" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Address:</label></td>
				<td><input type="text" name="address" class="form-control" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Email:</label></td>
				<td><input type="email" name="email" class="form-control" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Credit Card Number:</label></td>
				<td><input type=text pattern="[0-9]{13,16}" name="ccno" class="form-control" placeholder="13-16 digits" required/></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Credit Card Type:</label></td>
				<td><input type="text" name="cctype" class="form-control" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Maturity:</label></td>
				<td><input type="date" name="maturity" class="form-control" required /></td>
			</tr>
			<tr>
				<td></td>
				<td>
				<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Submit" class="btn btn-success" />
				</div>
				</td>
			</tr>
				</table>
	</form>
	</div>
	</div>
	</div>
	
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
