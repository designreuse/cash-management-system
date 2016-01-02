<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.css" />
<title>Invoice List</title>
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

	<h2 style="color: #337ab7;font-family: 'Helvetica Neue'">Invoice List</h2>
	<div class="table-responsive" style="margin: 0 auto !important;
  float: none !important">
	<table class="table table-striped text-left">
		<tr>
			<th>ID</th>
			<th>Merchant ID</th>
			<th>Sum Sent, $</th>
			<th>Formed Date</th>
			<th>Sent Date</th>
			<th>Status (C - Completed, P - Pending)</th>
		</tr>
		<c:forEach items="${invoiceList}" var="item">
			<tr>
				<td><c:out value="${item.id}" /></td>
				<td><c:out value="${item.merchantId}" /></td>
				<td><c:out value="${item.sumSent}" /></td>
				<td><c:out value="${item.formedDate}" /></td>
				<td><c:out value="${item.sentDate}" /></td>
				<td><c:out value="${item.status}" /></td>
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
	
	<a href="<c:url value="/forminvoices"/>" class="btn btn-success"><span class="glyphicon glyphicon-list-alt"></span> Form new invoices</a>
	</div>
	<div class="col-md-6 col-xs-6">
		<form action="<c:url value="/payinvoices"/>" method="POST" class="form-inline" role="form">
				<input type="number" min="0" step="any" class="form-control" name="availableFunds" size="40" placeholder="Available funds, $" required >
				<button type="submit" class="btn btn-warning">
  					<span class="glyphicon glyphicon-usd"></span> Pay the invoices
				</button>
		</form>
	</div>
	</div>
	</div>
	
	<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
