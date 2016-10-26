<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Users list</title>
		<style type="text/css">
			table { align: center;
				border: medium solid black;
				border-spacing: 1px 
			}
			td, th { border: thin dotted black;
				padding: 2px 
			}
			caption {
				font-style: italic;
				font-size: large;
			}
		</style>	
	</head>
	<body>
		<h3>Периодические издания</h3>
		<table>
		<caption>Список пользователей</caption>
			<tr>
				<th>№п/п</th>
				<th>Фамилия</th>
				<th>Имя</th>
				<th>Логин</th>
				
			</tr>
			<c:forEach var="user" items="${usersList}" varStatus="status">
				<tr>
					<td><c:out value="${ status.count }" /></td>
					<td><c:out value="${ user.surname }" /></td>
					<td><c:out value="${ user.forename }"/></td>
					<td><c:out value="${ user.login }"/></td>
				</tr>
			</c:forEach>
			
				
		</table>
		<br/><br/><br/>
		<form name="Logout" method="POST" action="controller">
			<input type="hidden" name="command" value="logout"/>
			Выйти из программы:<br/>
			<input type="submit" value="logout"/>
		</form>
	</body>
</html>