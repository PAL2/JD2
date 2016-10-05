<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>All bookings</title>
</head>
<body>
	<h3>All bookings</h3>
	<table border="1">
		<thead align="center">
			<tr>
				<th>ID</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Place</th>
				<th>Category</th>
				<th>Room Id</th>
				<th>User Id</th>
				<th>Account Id</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach items="${allBooking}" var="booking">
				<tr>
					<td><c:out value="${booking.bookingId}" /></td>
					<td><c:out value="${booking.startDate}" /></td>
					<td><c:out value="${booking.endDate}" /></td>
					<td><c:out value="${booking.place}" /></td>
					<td><c:out value="${booking.category}" /></td>
					<td><c:out value="${booking.roomId}" /></td>
					<td><c:out value="${booking.userId}" /></td>
					<td><c:out value="${booking.accountId}" /></td>
					<td><c:out value="${booking.status}" /></td>
					<td><form method="post" action="controller">
							<input type="hidden" name="command" value="deletebooking" /> <input
								type="hidden" name="booking_id" value="${booking.bookingId}">
							<input type="submit" value="Delete" />
						</form></td>
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
					<input type="hidden" name="command" value="allaccount" /> <input
						type="submit" value="Show all accounts" />
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