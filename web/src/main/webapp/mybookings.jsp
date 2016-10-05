<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>My bookings</title>
</head>
<body>
	<h3>My bookings</h3>
	<table border="1">
		<thead align="center">
			<tr>
				<th>ID</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Place</th>
				<th>Category</th>
				<th>Room Id</th>
				<th>Account Id</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach items="${bookingByUser}" var="booking">
				<tr>
					<td><c:out value="${booking.bookingId}" /></td>
					<td><c:out value="${booking.startDate}" /></td>
					<td><c:out value="${booking.endDate}" /></td>
					<td><c:out value="${booking.place}" /></td>
					<td><c:out value="${booking.category}" /></td>
					<td><c:out value="${booking.roomId}" /></td>
					<td><c:out value="${booking.accountId}" /></td>
					<td><c:out value="${booking.status}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<table>
		<tr>
			<td>
				<form method="post" action="controller">
					<div>
						<input type="hidden" name="command" value="mybooking" /> <input
							type="submit" value="My bookings" />
					</div>
				</form>
			</td>
			<td>
				<form method="post" action="controller">
					<div>
						<input type="hidden" name="command" value="unpaidaccount" /> <input
							type="submit" value="Unpaid accounts" />
					</div>
				</form>
			</td>
			<td>
				<form method="post" action="controller">
					<div>
						<input type="hidden" name="command" value="myaccounts" /> <input
							type="submit" value="All accounts" />
					</div>
				</form>
			</td>
			<td>
				<form method="post" action="controller">
					<div>
						<input type="hidden" name="command" value="goorder" /> <input
							type="submit" value="Go to booking" />
					</div>
				</form>
			</td>
		</tr>
	</table>
	<br>
	<form method="post" action="controller">
		<input type="hidden" name="command" value="Logout" /> <input
			type="submit" value="Log out" />
	</form>
</body>
</html>