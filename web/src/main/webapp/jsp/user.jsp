<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User</title>
	</head>
	<body>
		
		<h3>Периодические издания</h3>
		<h3>Страница пользователя</h3>
		<br/>
		<p>Выберете необходимое действие:</p>
		 <fieldset>
		 	<legend>Выбор периодических изданий</legend>
		 	<form name="PeriodicEditionsUser" method="POST" action="controller">
		 			<input type="hidden" name="command" value="periodicEditionsUser"/>
		 			<input type="submit" value="Приступить"/>
			</form>
		</fieldset>
		<br/><br/><br/>
		<form name="Logout" method="POST" action="controller">
			<input type="hidden" name="command" value="logout"/>
			Выйти из программы:<br/>
			<input type="submit" value="logout"/>
		</form>
	</body>
</html>