<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>All accounts</title>
</head>
<body>
	<h3>All accounts</h3>
	<br>
	<table border="1">
		<thead align="center">
			<tr>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Place</th>
				<th>Category</th>
				<th>Room Id</th>
				<th>Name</th>
				<th>Last Name</th>
				<th>Account Id</th>
				<th>Status</th>
				<th>Summa</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach items="${allBookings}" var="booking">
				<tr>
					<td><c:out value="${booking.startDate}" /></td>
					<td><c:out value="${booking.endDate}" /></td>
					<td><c:out value="${booking.place}" /></td>
					<td><c:out value="${booking.category}" /></td>
					<td><c:out value="${booking.roomId}" /></td>
					<td><c:forEach items="${allUsers}" var="user">
							<c:if test="${user.userId==booking.userId}">${user.firstName}</c:if>
						</c:forEach></td>
					<td><c:forEach items="${allUsers}" var="user">
							<c:if test="${user.userId==booking.userId}">${user.lastName}</c:if>
						</c:forEach></td>
					<td><c:out value="${booking.accountId}" /></td>
					<td><c:out value="${booking.status}" /></td>
					<td><c:forEach items="${allAccounts}" var="account">
							<c:if test="${account.accountId==booking.accountId}">${account.summa}$</c:if>
						</c:forEach></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<table>
		<tr>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="newbooking" /> <input
						type="submit" value="Show new bookings" />
				</form></td>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="allbooking" /> <input
						type="submit" value="Show all bookings" />
				</form></td>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="alluser" /> <input
						type="submit" value="Show all users" />
				</form></td>
			<td><form method="post" action="controller">
					<input type="hidden" name="command" value="allroom" /> <input
						type="submit" value="Show all rooms" />
				</form></td>
		</tr>
	</table>
	<br>
	<form method="post" action="controller">
		<input type="hidden" name="command" value="Logout" /> <input
			type="submit" value="Log out" />
	</form>
</body>
</html>