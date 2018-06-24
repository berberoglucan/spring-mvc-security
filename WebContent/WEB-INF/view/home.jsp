<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<h2>Home Page</h2>
	<hr>
	<p> Welcome to Home Page </p>
	<hr>
	<p>User : <security:authentication property="principal.username"/> </p>
	<p>Role(s) : <security:authentication property="principal.authorities"/> </p>
	<hr>
	
	<!-- add a link to point to /leaders... this is for the managers -->
	<!-- sadece rol MANAGER olan gorebilir -->
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting (Only for managers)</a>
		</p>
	</security:authorize>
	
	<!-- add a link to point to /systems... this is for the admins -->
	<!-- sadece rol ADMIN olan gorebilir -->
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Meeting (Only for admins)</a>
		</p>
	</security:authorize>
	
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		
		<input type="submit" value="Logout">
		
	</form:form>
</body>
</html>