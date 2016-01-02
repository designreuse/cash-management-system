<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<title>Customer List</title>
</head>
<body>
	<jsp:include page="../fragments/header.jsp"/>
	
	<div class="container-fluid">
	<div class="row">
	<div class="col-md-12 col-xs-12">
	<br>
	<a href="<c:url value="/"/>" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> Back</a>
	</div>
	</div>
	</div>
	
	<div class="container-fluid text-center">
	<div class="row">
	<div class="col-md-12 col-xs-12">
	
	<h2 style="color: #337ab7;font-family: 'Helvetica Neue'">Customer List</h2>
	<div class="table-responsive" style="margin: 0 auto !important;
  float: none !important">
	<table class="table table-striped text-left">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Address</th>
			<th>Email</th>
			<th>Credit Card Number</th>
			<th>Credit Card Type</th>
			<th>Maturity</th>
		</tr>
		<c:forEach items="${customerList}" var="item">
			<tr>
				<td><c:out value="${item.id}" /></td>
				<td><c:out value="${item.name}" /></td>
				<td><c:out value="${item.address}" /></td>
				<td><c:out value="${item.email}" /></td>
				<td><c:out value="${item.ccno}" /></td>
				<td><c:out value="${item.cctype}" /></td>
				<td><c:out value="${item.maturity}" /></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	</div>
	</div>
	<br>
	
	<div class="container-fluid text-center">
	<div class="row">
	<div class="col-md-6 col-xs-6">
	
	<a href="<c:url value="/addcustomer"/>" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Add a new customer</a>
	</div>
	<div class="col-md-6 col-xs-6">
		<form action="<c:url value="/removecustomer"/>" method="POST" class="form-inline" role="form">
				<input type="text" class="form-control" name="id" placeholder="Enter customer's ID" required >
				<button type="submit" class="btn btn-danger">
  					<span class="glyphicon glyphicon-remove"></span> Remove a customer
				</button>
		</form>
	</div>
	</div>
	</div>
	
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
