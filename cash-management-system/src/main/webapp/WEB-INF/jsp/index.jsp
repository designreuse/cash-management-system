<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
		<title>Cash Managing System</title>
	</head> 
	<body>
		<jsp:include page="fragments/header.jsp"/>
	
	<div class="container-fluid text-center">
	<div class="row">
	<div class="col-md-12 col-xs-12">
			
	<div class="list-group">
	<table class="table-responsive" style="margin: 0 auto !important;
  float: none !important">
  		<tr>
  			<td><h2><a href="merchants" class="list-group-item btn-primary">Merchants</a></h2></td>
  		</tr>
  		<tr>
  			<td><h2><a href="customers" class="list-group-item btn-primary">Customers</a></h2></td>
  		</tr>
  		<tr>
  			<td><h2><a href="payments" class="list-group-item btn-primary">Payments</a></h2></td>
  		</tr>
  		<tr>
  			<td><h2><a href="invoices" class="list-group-item btn-primary">Invoices</a></h2></td>
  		</tr>
  	</table>
	</div>
	
	</div>
	</div>
	</div>
	
	<jsp:include page="fragments/footer.jsp"/>
	</body>
</html>
