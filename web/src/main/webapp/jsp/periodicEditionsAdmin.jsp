<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Periodic editions admin work</title>
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
			label{color:green
			}
			.hide {
    			display: none; 
			}
			.hide + label ~ fieldset{
   				display: none;
			}
			.hide:checked + label + fieldset {
    			display: block; 
    		}			
		</style>	
	</head>
	<body>
		<h3>Периодические издания</h3>
		<table>
		<caption>Список периодических изданий</caption>
			<tr>
				<th>№п/п</th>
				<th style="width:5em">ISSN</th>
				<th>Название</th>
				<th style="width:5em">Кол-во выпусков в месяц</th>
				<th style="width:5em">Стоимость подписки в месяц</th>
				<th style="width:5em">Скидка на квартал</th>
				<th style="width:5em">Скидка на полугодие</th>
			</tr>
			<c:forEach var="periodicEdition" items="${periodicEditionsList}" varStatus="status">
				<tr>
					<td rowspan="3"><c:out value="${ status.count }" /></td>
					<td><c:out value="${ periodicEdition.id }" /></td>
					<th><c:out value="${ periodicEdition.title }"/></th>
					<td><c:out value="${ periodicEdition.monthPeriodicity }" /></td>
					<td><c:out value="${ periodicEdition.monthPrice }" /></td>
					<td><c:out value="${ periodicEdition.discountQuarteryear }" /></td>
					<td><c:out value="${ periodicEdition.discountHalfyear }" /></td>
				</tr>
				<tr>
					<td colspan="6"><c:out value="${ periodicEdition.shortDescription }" /></td>
				</tr>
				<tr>
					<td colspan="6">
						<form name="PeriodicEditionDelete" method="POST" action="controller">
		 					<input type="hidden" name="command" value="periodicEditionDelete"/>
		 					<input type="hidden" name="id" value="${ periodicEdition.id }"/>
							<input type="submit" value="Удалить"/>
						</form>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7">
					<input type="checkbox" id="hd-1" class="hide"/>
   					<label for="hd-1" >Нажмите чтобы добавить периодическое издание</label>
    				<fieldset>
		 				<legend>Форма добавления периодического издания</legend>
						<form name="PeriodicEditionCreate" method="POST" action="controller">
							<input type="hidden" name="command" value="periodicEditionCreate"/>
							<input type="text" name="ISSN" size="8" required autofocus placeholder="00000000" pattern="[0-9]{8}"/>
							<span>*</span>ISSN<br/>
							<input type="text" name="title"/>Название
							<br/>Краткое описание<br/>
							<textarea name="shortDescription" cols="50" rows="4"></textarea>
							<br/>
							<input type="text" name="monthPeriodicity" pattern="[0-9]{1,2}"/>Количесиво выпусков в месяц
							<br/>
							<input type="text" name="monthPrice"/>Стоимость подписки в месяц
							<br/>
							<input type="text" name="discountQuarteryear" pattern="[0-9]{0,3}"/>Скидка на квартал
							<br/>
							<input type="text" name="discountHalfyear" pattern="[0-9]{0,3}"/>Скидка на полугодие
							<br/>
							<input type="submit" value="Добавить"/>
						</form>
					</fieldset>
				</td>
			</tr>
		</table>
		<br/><br/><br/>
		<form name="Logout" method="POST" action="controller">
			<input type="hidden" name="command" value="logout"/>
			Выйти из программы:<br/>
			<input type="submit" value="logout"/>
		</form>
	</body>
</html>