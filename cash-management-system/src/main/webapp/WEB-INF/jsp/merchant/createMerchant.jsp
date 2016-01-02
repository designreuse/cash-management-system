<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<title>Add a new merchant</title>
</head>
<body>
	<jsp:include page="../fragments/header.jsp"/>

	<div class="container-fluid">
	<div class="row">
	<div class="col-md-12 col-xs-12">
	<br>
	<a href="<c:url value="/merchants"/>" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> Back</a>
	</div>
	</div>
	</div>
	
	<div class="container-fluid text-center">
	<div class="row">
	<div class="col-md-12 col-xs-12">
	<h2 style="color: #337ab7;font-family: 'Helvetica Neue'">Add a new merchant</h2>
	<c:url var="post_url"  value="/addmerchant" />
	<form action="${post_url}" method="POST" class="form-horizontal" role="form">
		<table style="margin: 0 auto !important; float: none !important">
			<tr>
				<td><label class="control-label col-sm-2">Name:</label></td>
				<td><input type="text" name="name" class="form-control" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Bank:</label></td>
				<td><input type="text" name="bankName" class="form-control" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Swift code:</label></td>
				<td><input type="text" name="swift" class="form-control" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Account:</label></td>
				<td><input type="text" name="account" class="form-control" required/></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Charge, %:</label></td>
				<td><input type="number" min="0" max="100" name="charge" class="form-control" placeholder="0-100" step="any" required /></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Period:</label></td>
				<td><select name="period" class="form-control" required >
						<option value="1">Weekly</option>
						<option value="2">Every 10 days</option>
						<option value="3">Monthly</option>
				</select></td>
			</tr>
			<tr>
				<td><label class="control-label col-sm-2">Minimal sum of transfer, $:</label></td>
				<td><input type="text" name="minSum" class="form-control" required /></td>
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
