<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login</title>
	</head>
	<body>
		<h3>Периодические издания 9</h3>
		<form name="LoginForm" method="POST" action="controller">
			<input type="hidden" name="command" value="login"/>
			<fieldset>
				<legend>Авторизируйтесь</legend>
				<input type="text" name="login" size="21" maxlength="20" placeholder="Логин" required/>
				<span>*</span>Логин
				<br/><br/>
				<input type="password" name="password" size="21" maxlength="20" placeholder="Пароль" required/>
				<span>*</span>Пароль
				<br/><br/>
				<input type="submit" value="Войти">
				<br/>
				${errorLoginPassMessage}
				${wornAction}
			</fieldset>
		</form>
	</body>
</html>