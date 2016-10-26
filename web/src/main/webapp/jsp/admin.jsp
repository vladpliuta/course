<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin</title>
	</head>
	<body>
		<h3>Периодические издания</h3>
		<h3>Страница администратора</h3>
		<br/>
		<p>Выберете необходимое действие:</p>
		 <fieldset>
		 	<legend>Редактировать базу</legend>
		 	<form name="PeriodicEditionsAdmin" method="POST" action="controller">
		 			<input type="hidden" name="command" value="periodicEditionsAdmin"/>
					<input type="submit" value="Редактировать"/>
			</form>
			
		</fieldset>
		
			<br/>
		<form name="UsersList" method="POST" action="controller">
			<fieldset>
		 		<legend>Просмотреть список пользователей</legend>
				<input type="hidden" name="command" value="usersList"/>
				<input type="submit" value="Пользователи"/>
			</fieldset>
		</form>
		<br/><br/><br/>
		<form name="Logout" method="POST" action="controller">
			<input type="hidden" name="command" value="logout"/>
			Выйти из программы:<br/>
			<input type="submit" value="logout"/>
		</form>
	</body>
</html>