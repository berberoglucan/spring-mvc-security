<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Custom Login Page</title>
</head>
<body>
	<h3>My Custom Login</h3>
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
		
		<!-- check for login error -->
		
		<c:if test="${param.error ne null}">
			
			<c:out value="Sorry! You entered invalid username/password"/>
			
		</c:if>
		
		<p>
			User name : <input type="text" name="username">
		</p>
		<p>
			Password : <input type="password" name="password">
		</p>
		<p>
			<input type="submit" value="Login">
		</p>
	
	</form:form>
	
	
</body>
</html>