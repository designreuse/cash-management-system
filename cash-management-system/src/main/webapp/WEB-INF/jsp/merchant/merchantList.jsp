<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.css" />
<title>Merchant List</title>
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
	
	<h2 style="color: #337ab7;font-family: 'Helvetica Neue'">Merchant List</h2>
	<div class="table-responsive" style="margin: 0 auto !important;
  float: none !important">
	<table class="table table-striped text-left">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Charge, %</th>
			<th>Period</th>
			<th>Minimum Sum, $</th>
			<th>Bank</th>
			<th>Swift</th>
			<th>Account</th>
			<th>Need To Send, $</th>
			<th>Sent, $</th>
			<th>Last Invoice Formed</th>
		</tr>
		<c:forEach items="${merchantList}" var="item">
			<tr>
				<td><c:out value="${item.id}" /></td>
				<td><c:out value="${item.name}" /></td>
				<td><c:out value="${item.charge}" /></td>
				<td><c:out value="${item.period}" /></td>
				<td><c:out value="${item.minSum}" /></td>
				<td><c:out value="${item.bankName}" /></td>
				<td><c:out value="${item.swift}" /></td>
				<td><c:out value="${item.account}" /></td>
				<td><c:out value="${item.needToSend}" /></td>
				<td><c:out value="${item.sent}" /></td>
				<td><c:out value="${item.lastInvoiceFormed}" /></td>
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
	
	<a href="<c:url value="/addmerchant"/>" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Add a new merchant</a>
	</div>
	<div class="col-md-6 col-xs-6">
		<form action="<c:url value="/removemerchant"/>" method="POST" class="form-inline" role="form">
				<input type="text" class="form-control" name="id" placeholder="Enter merchant's ID" required >
				<button type="submit" class="btn btn-danger">
  					<span class="glyphicon glyphicon-remove"></span> Remove a merchant
				</button>
		</form>
	</div>
	</div>
	</div>
	
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
